package application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main (String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre os dados do contrato:");
        System.out.print("Numero: ");
        int number = sc.nextInt();
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(),fmt);
        System.out.print("Valor do contrato: ");
        double contractValue = sc.nextDouble();

        Contract contract = new Contract(number,date, contractValue);

        System.out.print("Entre com o numero de parcelas: ");

        int n = sc.nextInt();

        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(contract,n);

        System.out.println("Parcelas: ");

        for(Installment installment : contract.getInstallments()){
            System.out.println(installment);
        }

        sc.close();

    }
}
