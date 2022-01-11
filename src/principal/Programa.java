package principal;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

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
				//split recortar string em dois com base na v�rgula para acessar nome e pre�o, vai ficar no vetor fields
				String[] fields = line.split(",");
				//fields na posi��o 0 nome; fields na posi��o 1 pre�o
				list.add(new Product(fields[0], Double.parseDouble(fields[1])));
				line = br.readLine();
			}
			
			/* mostrar o pre�o m�dio de todos os produtos:
			 1- pegar cada um dos pre�os e somar- 2- dividir pelo tamanho da lista
			 3- tem pelo menos um elemento na� tem diviz�o por zero	 */
			
			double avg = list.stream().map(p -> p.getPreco())
					.reduce(0.0, (x,y) -> x+y)/ list.size();
			System.out.println("Pre�o M�dio: " + String.format("%.2f", avg));
			/* mostrar os nomes dos produtos, na ordem decrescente dos produtos que possuem pre�os
			 inferior ao pre�o m�dio.
			 1-criar uma nova lista String - 2- filtrar todos que tem o pre�o a baixo da m�dia 3- map para pegar
			 o nome de cada produto 4- (sort comparator- declarar em cima) ordenar em ordem decrescente de nomes*/
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> nomes = list.stream().filter(p -> p.getPreco() < avg)
					.map(p -> p.getNome()).sorted(comp.reversed())
					.collect(Collectors.toList());
			nomes.forEach(System.out::println);
					
			
		}catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
			sc.close();
		}
	}


