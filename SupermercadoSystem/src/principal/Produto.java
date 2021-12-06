package principal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Produto {
	String nome, descricao;
	String fornecedor;
	Date data;
	double precoCompra, precoVenda;
	int estoque;
	
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
	
	void passarData(String dataString) {
		try {
			this.data = formato.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	Produto(String nome){
		this.nome = nome;
	}
	
	Produto(String nome, String descricao, String data, String fornecedor,
		double precoCompra,double precoVenda,int estoque){
		this.nome = nome;
		this.descricao =  descricao;
		try {
			this.data = formato.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.fornecedor = fornecedor;
		this.precoCompra = precoCompra;
		this.precoVenda = precoVenda;
		this.estoque = estoque;
	}
	String dataFormatada() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = dateFormat.format(this.data);
		return dataFormatada;
	}
}
