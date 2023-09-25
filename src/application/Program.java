package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Contribuinte;
import entities.PessoaFisica;
import entities.PessoaJuridica;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Contribuinte> lista = new ArrayList<>();

		System.out.print("Entre com o número de contribuintes: ");
		int n = sc.nextInt();

		for (int i = 1; i <= n; i++) {

			char contribuinteType;

			do {

				System.out.print("Pessoa física ou jurídica (pf/pj)? ");
				contribuinteType = sc.next().charAt(1);

				if (contribuinteType != 'f' && contribuinteType != 'j') {
					System.out.println("Tipo não identificado, utilizar o padrão (pf/pj). Tente novamente.");
				}

			} while (contribuinteType != 'f' && contribuinteType != 'j');

			System.out.println("Contribuinte #" + i + " dados:");
			System.out.print("Nome: ");
			sc.nextLine();
			String nome = sc.nextLine();
			System.out.print("Renda Anual: ");
			Double rendaAnual = sc.nextDouble();

			if (contribuinteType == 'f') {
				System.out.print("Gastos com saúde: ");
				Double gastosSaude = sc.nextDouble();
				lista.add(new PessoaFisica(nome, rendaAnual, gastosSaude));

			} else {
				System.out.print("Número de funcionários: ");
				int nFuncionarios = sc.nextInt();
				lista.add(new PessoaJuridica(nome, rendaAnual, nFuncionarios));
			}

		}

		System.out.println();
		System.out.println("Impostos pagos: ");

		for (Contribuinte contribuinte : lista) {
			System.out.println(contribuinte.getNome() + ": $" + String.format("%.2f", contribuinte.imposto()));

		}

		double sum = 0.0;
		for (Contribuinte contribuinte : lista) {
			sum += contribuinte.imposto();
		}

		System.out.println();
		System.out.printf("IMPOSTO TOTAL: $ %.2f", sum);

		sc.close();
	}

}
