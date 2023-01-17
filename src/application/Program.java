package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrasilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("Entre com o preço por hora: ");
		Double precoHora = sc.nextDouble();
		System.out.print("Entre com o preço por dia:  ");
		Double precoDia = sc.nextDouble();

		RentalService rentalService = new RentalService(precoHora, precoDia, new BrasilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println("FATURA");
		System.out.print("Pagamento basico: " + cr.getInvoice().getBasicPayment()+"\n");
		System.out.print("Imposto: " + cr.getInvoice().getTax()+"\n");
		System.out.print("Pagamento total: " + cr.getInvoice().getTotalPayment()+"\n");
		sc.close();
	}

}
