import { ActivatedRoute } from '@angular/router';
import { OnInit, Component, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { ToastyService } from 'ng2-toasty';

import { Documento } from '../../../model/documento.model';
import { DocumentosService } from '../../../service/documentos.service';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';

@Component({
  selector: 'app-documentos',
  templateUrl: './documentos.component.html',
  styleUrls: ['./documentos.component.css']
})
export class DocumentosComponent implements OnInit {

  @ViewChild('arquivo') arquivo;

  @ViewChild('tableAnexo') tableAnexo;

  documento: Documento = new Documento();

  formDocumento: FormGroup;

  documentos: Documento[] = [];

  tipos: any[];

  codigoAluno: number;

  constructor(
    private toasty: ToastyService,
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private confirmartion: ConfirmationService,
    private documentosService: DocumentosService,
  ) { }

  ngOnInit() {
    this.codigoAluno = this.activatedRoute.snapshot.params['codigo'];

    this.formDocumento = this.formBuilder.group({
      'descricao': [null, Validators.required],
      'tipo': [null, Validators.required],
    });
    this.carregaTipoRquivo();
    this.listarAnexos();
  }

  private carregaTipoRquivo() {
    this.tipos = [{ valeu: null, label: 'Selecione..' }, { value: 'TIPO_A', label: 'Tipo A' }, { value: 'TIPO_B', label: 'Tipo B' }];
  }

  listarAnexos() {
    this.documentosService.listar(this.codigoAluno)
      .subscribe(lista => {
        this.documentos = lista;
      });
  }

  selecionaAnexo() {

    const file: File = this.arquivo.nativeElement.files[0];

    if ((file.size / 1024 / 1024) > 25) {
      this.toasty.warning('O arquivo fornecido deve possui o tamanho máximo de 25MB');
    } else {

      const fileReader: FileReader = new FileReader();

      fileReader.onloadend = () => {
        const nomeCompleto = this.arquivo.nativeElement.files[0].name;
        this.documento.nome = nomeCompleto.substr(0, nomeCompleto.lastIndexOf('.'));
        this.documento.extensao = nomeCompleto.substr(nomeCompleto.lastIndexOf('.'));
        const base64: string = fileReader.result.toString();
        const base64Data: string[] = base64.split('base64,');
        this.documento.anexoBase64 = base64Data[1];
      };

      fileReader.readAsDataURL(file);
    }
  }

  adicinarAnexo() {
    if (this.formDocumento.valid && this.arquivo.nativeElement.files[0]) {
      if (!this.validaExtensoes(this.documento)) {
        this.toasty.warning('Extensão de arquivo não permitida');
        return;
      }
      this.documento.codigo = null;
      this.documento.codigoAluno = this.codigoAluno;
      this.documento.descricao = this.formDocumento.get('descricao').value;
      this.documento.tipo = this.formDocumento.get('tipo').value;
      this.documentos.push(this.documento);
      this.tableAnexo.value = this.documentos;
      this.limpaCampos();
    } else {
      this.toasty.warning('Há campos obrigatórios não preenchidos!');
    }
  }

  private validaExtensoes(documento: Documento): boolean {
    return ['.pdf', '.odt', '.xlx', '.xlsx', '.txt', '.zip', '.doc', '.docx', '.png', '.jpeg', '.png', '.jpg']
      .includes(documento.extensao);
  }

  private limpaCampos() {
    this.documento = new Documento();
    this.formDocumento.reset();
    this.arquivo.nativeElement.value = null;
  }

  salvaAnexos() {
    if (this.documentos.length > 0 && this.verificaSeHaDadosSalvar()) {
      this.documentosService.upload(this.documentos)
        .subscribe(() => this.listarAnexos());
    }
  }

  download(documento: Documento) {
    this.documentosService.download(documento.caminho)
      .subscribe(blob => this.downloadArquivo(blob, documento));
  }

  downloadArquivo(blob: any, documento: Documento) {
    const url = window.URL.createObjectURL(blob);
    const element = window.document.createElement('a');
    document.body.appendChild(element);
    element.href = url;
    element.download = `${documento.nome}${documento.extensao}`;
    element.click();
    document.body.removeChild(element);
  }

  confirmarExclusao(documento: Documento) {
    this.confirmartion.confirm({
      message: `Deseja excluir o documento: ${documento.nome}${documento.extensao} ?`,
      header: 'Excluir documento',
      icon: 'fa fa-question-circle',
      accept: () => this.exclui(documento),
      reject: () => { }
    });
  }

  private exclui(documento: Documento) {
    const indice = this.documentos.indexOf(documento);
    if (documento.codigo) {
      this.documentosService.delete(documento.codigo)
        .subscribe(() => this.removeRegistroLista(indice));
    } else {
      this.removeRegistroLista(indice);
    }
  }

  private removeRegistroLista(indice: number) {
    this.documentos.splice(indice, 1);
    this.tableAnexo.value = this.documentos;
    this.toasty.success('Excluido com sucesso!');
  }

  private verificaSeHaDadosSalvar(): boolean {
    const result = this.documentos
      .find(doc => {
        return doc.codigo === null;
      });
    return result !== undefined;
  }

}
