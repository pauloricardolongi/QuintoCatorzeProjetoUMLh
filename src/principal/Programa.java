package principal;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import aplicacao.Product;

public class Programa {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Entre com o caminho do arquivo");
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Product> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null){
				//split recortar string em dois com base na vírgula para acessar nome e preço, vai ficar no vetor fields
				String[] fields = line.split(" , ");
				//fields na posição 0 nome; fields na posição 1 preço
				list.add(new Product(fields[0], Double.parseDouble(fields[1])));
				line = br.readLine();
			}
			
			/* mostrar o preço médio de todos os produtos:
			 1- pegar cada um dos preços e somar- 2- dividir pelo tamanho da lista
			 3- tem pelo menos um elemento naõ tem divizão por zero	 */
			
			double avg = list.stream().map(p -> p.getPreco())
					.reduce(0.0, (x,y) -> x+y)/ list.size();
			System.out.println("Preço Médio: " + String.format("%.2f", avg));
			
		}catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
			sc.close();
		}
	}


