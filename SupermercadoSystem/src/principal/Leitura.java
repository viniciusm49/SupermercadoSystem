package principal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Leitura {
	public static Map<Integer, Pessoa> leituraDePessoas() {
		Map<Integer, Pessoa> genteVetor = new HashMap<>();

		try {
			int i = 1;
			FileReader fr = new FileReader("C:\\Users\\vinic\\Desktop\\SupermercadoSystem\\src\\bancodedados\\listaPessoas.txt");
			BufferedReader br = new BufferedReader(fr);
			String temp;
			String[] strA = null;
			while ((temp = br.readLine()) != null)
			{
				strA = temp.split(",");
				genteVetor.put(i, new Pessoa(strA[0],strA[1],Long.parseLong(strA[2]),Long.parseLong(strA[3])));
				i++;
			}
			br.close();
		}
		catch (FileNotFoundException el)
		{
			System.out.println("Arquivo não Encontrado!");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return genteVetor;
	}
	
	public static Map<Integer, Produto> leituraDeProdutos() {
		Map<Integer, Produto> produtoMapRascunho = new HashMap<>();
		try {
			int i = 1;
			FileReader fr = new FileReader("C:\\Users\\vinic\\Desktop\\SupermercadoSystem\\src\\bancodedados\\listaProdutos.txt");
			BufferedReader br = new BufferedReader(fr);
			String temp;
			String[] strA = null;
			while ((temp = br.readLine()) != null)
			{
				strA = temp.split(",");
				produtoMapRascunho.put(Integer.parseInt(strA[0]), new Produto(strA[1],strA[2],strA[3],strA[4],
Double.parseDouble(strA[5]), Double.parseDouble(strA[6]), Integer.parseInt(strA[7])));
				i++;
			}
			br.close();
		}
		catch (FileNotFoundException el)
		{
			System.out.println("Arquivo não Encontrado!");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return produtoMapRascunho;
	}
}
