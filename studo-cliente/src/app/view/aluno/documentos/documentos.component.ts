import { OnInit, Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { STUDO_API } from '../../../app.api';

import { AuthService } from '../../../service/auth.service';
import { DocumentosService } from '../../../service/documentos.service';

@Component({
  selector: 'app-documentos',
  templateUrl: './documentos.component.html',
  styleUrls: ['./documentos.component.css']
})
export class DocumentosComponent implements OnInit {

  listaDocumentos: any[] = [];
  documentos: any[] = [];
  uploadURL: string;
  codigoAluno: number;

  constructor(
    private auth: AuthService,
    private activatedRoute: ActivatedRoute,
    private documentosService: DocumentosService
  ) { }

  ngOnInit() {
    this.codigoAluno = this.activatedRoute.snapshot.params['codigo'];
    this.uploadURL = `${STUDO_API}/documentos/upload/${this.codigoAluno}`;
    this.buscaDocumentos(this.codigoAluno);
  }

  buscaDocumentos(codigo: number) {
    this.documentosService.listar(codigo).then(documentos => {
      this.listaDocumentos = documentos;
    });
  }

  onBeforeSend(event) {
    event.xhr.setRequestHeader('Authorization', `Bearer ${this.auth.carregarToken()}`);
  }

  onUpload(event) {
    for (const arquivo of event.files) {
      this.documentos.push(arquivo);
    }
    this.buscaDocumentos(this.codigoAluno);
  }

  download(documento: any) {
    this.documentosService.download(documento.codigo).subscribe(blob => {
      this.downloadArquivo(blob, documento.nome);
    });
  }

  downloadArquivo(blob: any, nomeArquivo: string) {
    const url = window.URL.createObjectURL(blob);
    const element = window.document.createElement('a');
    document.body.appendChild(element);

    element.href = url;
    element.download = nomeArquivo;
    element.click();
    document.body.removeChild(element);
  }
}
