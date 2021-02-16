/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.pagamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Calcular os dias que pode se pagar uma conta a partir da data informada,
 * considerando 10 dias sem juros, caso o decimo dia seja no sabado ou domingo
 * informar a data do proxima segunda
 *
 * @author Ronaldo
 */
public class TrabalhandoClasseCalendar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {

        Scanner leitor = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Digite data de vencimento da fatura: dd/MM/yyyy ->");
        String dateInString = leitor.nextLine();
        Date date = sdf.parse(dateInString);
        TrabalhandoClasseCalendar obj = new TrabalhandoClasseCalendar();

        Calendar calendar = obj.dateToCalendar(date);
        System.out.println("data" + calendar.getTime());
        Date newDate = obj.calendarToDate(calendar);

        //acrescentando 10 dias
        calendar.add(Calendar.DATE, 10);

        // pegando o dia da semana após acrescentar os 10 dias
        int diaSemana = obj.dayWeek(calendar);

        switch (diaSemana) {
            case 7 -> {
                //somando mais dois dias - próxima segunda
                calendar.add(Calendar.DATE, 2);
                //System.out.println("Data limite para pagamento sem multa: " + calendar.getTime());
                System.out.printf("Data limite para pagamento sem multa: %tD\n", calendar);
            }
            case 1 -> {
                //somando mais um dia - próxima segunda
                calendar.add(Calendar.DATE, 1);
                //System.out.println("Data limite para pagamento sem multa: " + calendar.getTime());
                System.out.printf("Data limite para pagamento sem multa: %tD\n", calendar);
            }
            default -> System.out.printf("Data limite para pagamento sem multa: %tD\n", calendar);
        }
    }

    private int dayWeek(Calendar calendar) {
        int diaSemana = calendar.get(calendar.DAY_OF_WEEK);
        return diaSemana;
    }

    private Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private Date calendarToDate(Calendar calendar) {
        return calendar.getTime();
    }

}
