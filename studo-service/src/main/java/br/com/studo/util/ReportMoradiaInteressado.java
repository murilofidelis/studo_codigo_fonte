package br.com.studo.util;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

public class ReportMoradiaInteressado {

    public void build() {

        int x = 19;

        JasperReportBuilder report = DynamicReports.report();

        report.setTemplate(Templates.reportTemplate)

                .title(Templates.createTitleComponent("Relatório Informativo").setStyle(Templates.rootStyle))

                .title(cmp.text("Relatório informativo").setStyle(Templates.bold12CenteredStyle))

                .setPageFormat(PageType.A4, PageOrientation.PORTRAIT)

                .columns(
                        col.column("CPF", "cpf", type.stringType()).setStyle(Templates.vmiddle).setWidth(20),
                        col.column("NIS", "nis", type.stringType()).setStyle(Templates.vmiddle).setWidth(20),
                        col.column("Tipo Morador", "tipo", type.stringType()).setStyle(Templates.vmiddle).setWidth(20),
                        col.column("Nome Completo", "nome", type.stringType()).setStyle(Templates.hleft).setWidth(40))

                .pageFooter().summary(cmp.text("\n Total: " + x + " registros").setStyle(Templates.boldStyle)
        );

        report.setDataSource(createDataSource());

        try {
            //  report.toPdf(new FileOutputStream("C:/Users/Murilo/Desktop/report.pdf"));

            report.show();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório de moradores com indicativo de óbtito");
        }
    }


    public static void main(String[] args) throws Exception {
        ReportMoradiaInteressado tt = new ReportMoradiaInteressado();
        tt.build();

        //  String s=     formatarString("03520683105","###.###.###-##");
        //System.out.println(s);
    }

    public JRDataSource createDataSource() {
        List<ReportDataRetornoCaixaDTO> l = new ArrayList<>();
        for (long i = 0; i <= 10; i++) {
            ReportDataRetornoCaixaDTO r = new ReportDataRetornoCaixaDTO();
            r.setCpf(formatarCpf("03520683105","###.###.###-##"));
            r.setNis("13213131312");
            r.setTipo("MORADOR");
            r.setNome("JOÃO DA SILVA PEREIRA CARDOSO");
            l.add(r);
        }
        return new JRBeanCollectionDataSource(l);
    }


    public static String formatarCpf(String texto, String mascara) {
        try {
            MaskFormatter mf = new MaskFormatter(mascara);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(texto);
        }catch (Exception e){}
    return  texto;
    }


}

