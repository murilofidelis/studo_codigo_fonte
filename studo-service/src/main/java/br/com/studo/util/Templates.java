package br.com.studo.util;

import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;

import java.awt.*;
import java.io.File;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.template;


public class Templates {

    private Templates() {
    }

    public static final StyleBuilder vmiddle;
    public static final StyleBuilder hleft;
    public static final StyleBuilder bold12CenteredStyle;


    public static final StyleBuilder rootStyle;
    public static final StyleBuilder boldStyle;

    public static final StyleBuilder columnStyle;
    public static final StyleBuilder columnTitleStyle;
    public static final ReportTemplateBuilder reportTemplate;
    public static final ComponentBuilder dynamicReportsComponent;
    //public static final ComponentBuilder footerComponent;

    public static final StyleBuilder backgroundCartao;

    static {

        vmiddle = stl.style().setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
                .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);

        hleft = stl.style().setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);

        rootStyle = stl.style().setPadding(5);
        boldStyle = stl.style(rootStyle).bold();

        bold12CenteredStyle = stl.style(boldStyle)
                .setFontSize(12).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);

        columnStyle = stl.style(rootStyle).setVerticalTextAlignment(VerticalTextAlignment.MIDDLE);

        columnTitleStyle = stl.style(columnStyle)
                .setBorder(stl.pen1Point())
                .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT)
                .setBackgroundColor(Color.white)
                .bold();

        reportTemplate = template()
                //  .setLocale(Locale.ENGLISH)
                .setColumnStyle(columnStyle)
                .setColumnTitleStyle(columnTitleStyle)
                .highlightDetailEvenRows()
                .crosstabHighlightEvenRows();


        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource("img/logo-cartao-reforma-completo.png").getFile());

        backgroundCartao = stl.style()
                .setBackgroundColor(new Color(3, 169, 244));

        dynamicReportsComponent =
                cmp.horizontalList(
                        cmp.image(file.getPath()).setStyle(backgroundCartao));

      /*  footerComponent = cmp.pageXofY()
                .setStyle(
                        stl.style(boldStyle)
                                .setTopBorder(stl.pen1Point())); */

    }

    /**
     * Creates custom component which is possible to add to any report band component
     */
    public static ComponentBuilder createTitleComponent(String label) {

        StyleBuilder title = stl.style(boldStyle)
                .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                .setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)
                .setFontSize(16)
                .setForegroundColor(new Color(255, 255, 255));


        return cmp.horizontalList().setFixedDimension(570, 0).setStyle(backgroundCartao)
                .add(
                        dynamicReportsComponent,
                        cmp.text(label).setStyle(title).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER))
             .newRow()
               .add(cmp.line())
               .newRow()
        .add(cmp.text(""))
               .add(cmp.verticalGap(20));
    }


}
