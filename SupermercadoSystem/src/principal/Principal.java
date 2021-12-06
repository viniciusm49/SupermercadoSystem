package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Relatorio{
	static double vendas;
	static ArrayList<String> relatorioEscrito = new ArrayList<>();
}

public class Principal {
	
	public static void main(String[] args){
		Scanner entrada = new Scanner(System.in);
		int aux;
		//Declarações e leitura
		Map<Integer, Pessoa> listaPessoas = new HashMap<>();
		Map<Integer, Produto> listaProdutos = new HashMap<>();
		Map<Integer, Caixa> listaCaixa = new HashMap<>();
		Map<Integer, Cliente> listaCliente = new HashMap<>();
		Map<Integer, Empresa> listaEmpresa = new HashMap<>();
		Map<Integer, Fornecedor> listaFornecedor = new HashMap<>();
		Map<Integer, Repositor> listaRepositor = new HashMap<>();
		listaPessoas = Leitura.leituraDePessoas();
		listaProdutos = Leitura.leituraDeProdutos();
	
		//Começo da logica
		boolean chaveGeral = true;
		while(chaveGeral) {
			System.out.println("\n--------------------------------------\n"
					+ "Bem vindo ao Sistema de Supermercado");
			System.out.println("Escolha um modo de login:");
			System.out.println("1-Administrador (Menu de Cadastro)\n2-Caixa\n3-Repositor\n4-Encerrar"
					+ "\n--------------------------------------");
			int num = entrada.nextInt();
			switch (num) {
			case 1:
				System.out.println("1-Menu de Cadastro\n2-Relatorio de vendas\n3-Voltar\n"
						+ "--------------------------------------");
				int menuAdm = entrada.nextInt();
				boolean chaveAdmGeral = true;
				while(chaveAdmGeral) {
					if(menuAdm == 1) {
						boolean chaveAdmCad = true;
						while(chaveAdmCad) {
							aux = menuCadastro();
							switch (aux) {
							case 1:
								cadastroPessoas(listaPessoas);
								break;
							case 2:
								cadastroCliente(listaCliente, listaPessoas);
								break;
							case 3:
								cadastroEmpresa(listaEmpresa, listaPessoas);
								break;
							case 4:
								cadastroFornecedor(listaFornecedor, listaPessoas, listaEmpresa);
								break;
							case 5:
								cadastroCaixa(listaCaixa, listaPessoas);
								break;
							case 6:
								cadastroRepositor(listaRepositor, listaPessoas);
								break;
							case 7:
								operProdutos(listaProdutos);
								break;
							case 8:
								chaveAdmGeral= false;
								chaveAdmCad = false;
								break;
							default:
								System.out.println("Número inválido");
							}
						}
					}else if(menuAdm == 2) {
						exibirRelatorio();
						chaveAdmGeral = false;
					}else if (menuAdm == 3) {
						chaveAdmGeral = false;
						System.out.println("--------------------------------------\n");
					}else {
						System.out.println("Codigo invalido");
					}
				}
				break;
			case 2:
				System.out.println("Digite seu codigo de caixa:");
				aux = entrada.nextInt();
				if(listaCaixa.containsKey(aux)) {
					System.out.println("Agora digite o numero da sua carteira de trabalho:");
					int numeroCarteira = entrada.nextInt();
					if(numeroCarteira == listaCaixa.get(aux).clt) {
						boolean chaveCaixa = true;
						System.out.println("--------------------------------------\n"
								+ "Login Realizado com Sucesso\nBem vindo " + listaCaixa.get(aux).pessoaCaixa.nome);
						while(chaveCaixa) {
							System.out.println("\nGostaria de? \n1-Exibir Produtos\n2-Realizar Venda\n3-Encerrar Sessão e voltar");
							int numCaixa = entrada.nextInt();
							  switch (numCaixa) {
							     case 1:
							       exibirProdutos(listaProdutos);
							       break;
							     case 2:
							       operVenda(listaProdutos, listaCliente);
							       break;
							     case 3:
							       chaveCaixa = false;
							       break;
							     default:
							       System.out.println("Número inválido");
							  }
						}
					}else {
						System.out.println("Falha no Login!");
					}
				}else {
					System.out.println("Numero inexistente");
				}
				break;
			case 3:
				System.out.println("Digite seu codigo de repositor:");
				aux = entrada.nextInt();
				if(listaRepositor.containsKey(aux)) {
					System.out.println("Agora digite o numero da sua carteira de trabalho:");
					int numeroCarteiraRep = entrada.nextInt();
					if(numeroCarteiraRep == listaRepositor.get(aux).clt) {
						boolean chaveRepositor = true;
						System.out.println("Login Realizado com sucesso!\nBem vindo " + listaRepositor.get(aux).pessoaRepositor.nome);
						while(chaveRepositor){
							System.out.println("Você gostaria de? \n1-Exibir produtos\n2-Repor estoque\n3-Voltar");
							int numRepositor = entrada.nextInt();
							switch (numRepositor) {
							case 1:
								exibirProdutos(listaProdutos);
								break;
							case 2:
								operRepor(listaProdutos);
								break;
							case 3:
								chaveRepositor = false;
								break;
							default:
								System.out.println("Número inválido");
							}
						}
					}else {
						System.out.println("Login invalido");
					}
				}else {
					System.out.println("Não existe repositor com esse codigo!");
				}
				break;
			case 4:
				chaveGeral = false;
				System.out.println("Programa encerrado com sucesso!");
				break;
			default:
				System.out.println("Número inválido");
			}

		}
		
	}
	//Operações do programa
	public static Map<Integer,Produto> operProdutos(Map<Integer, Produto>listaAntiga){
		Scanner entrada = new Scanner(System.in);
		System.out.println("#######Sistema de Cadastro de Produtos#######");
		boolean chave = true;
		int aux;

		while(chave) {
			System.out.println("O que gostaria de fazer?\n1-Cadastrar\n2-Alterar\n3-Excluir\n4-Voltar ao Menu Principal");
			int num = entrada.nextInt();
			switch (num) {
			case 1:
				System.out.println("Digite o codigo de cadastro para o novo produto:");
				aux = entrada.nextInt();
				if(listaAntiga.containsKey(aux)) {
					System.out.println("Codigo já utilizado!");
				}else{
					System.out.println("Digite o nome do produto:");
					listaAntiga.put(aux, new Produto(entrada.next()));
					System.out.println("Digite a descrição do produto:");
					listaAntiga.get(aux).descricao = entrada.next();
					System.out.println("Digite a data de validade: dd/mm/aaaa");
					listaAntiga.get(aux).passarData(entrada.next());
					System.out.println("Digite o fornedor:");
					listaAntiga.get(aux).fornecedor = entrada.next();
					System.out.println("Informe o preço de compra:");
					listaAntiga.get(aux).precoCompra = entrada.nextDouble();
					System.out.println("Informe o preço de venda:");
					listaAntiga.get(aux).precoVenda = entrada.nextDouble();
					System.out.println("Informe a quantidade em estoque:");
					listaAntiga.get(aux).estoque = entrada.nextInt();
					System.out.println("Produto Cadastrado com Sucesso!");
				}
				break;
			case 2:
				System.out.println("Digite o codigo de cadastro do produto:");
				aux = entrada.nextInt();
				if(listaAntiga.containsKey(aux)) {
					System.out.println("Digite o novo nome do produto:");
					listaAntiga.get(aux).nome = entrada.next();
					System.out.println("Digite a nova descrição do produto:");
					listaAntiga.get(aux).descricao = entrada.next();
					System.out.println("Digite a nova data de validade: dd/mm/aaaa");
					listaAntiga.get(aux).passarData(entrada.next());
					System.out.println("Digite o novo fornedor:");
					listaAntiga.get(aux).fornecedor = entrada.next();
					System.out.println("Informe o novo preço de compra:");
					listaAntiga.get(aux).precoCompra = entrada.nextDouble();
					System.out.println("Informe o novo preço de venda:");
					listaAntiga.get(aux).precoVenda = entrada.nextDouble();
					System.out.println("Informe a nova quantidade em estoque:");
					listaAntiga.get(aux).estoque = entrada.nextInt();
					System.out.println("Produto Alterado com Sucesso!");
				}else {
					System.out.println("Produto inexistente!");
				}

				break;
			case 3:
				System.out.println("Digite o codigo de cadastro do produto:");
				aux = entrada.nextInt();
				if(listaAntiga.containsKey(aux)) {
					System.out.printf("Objeto %s removido com sucesso!\n",listaAntiga.remove(aux).nome);
				}else {
					System.out.println("Codigo inexistente");
				}
				break;
			case 4:
				System.out.println("Retornando ao Menu Principal..");
				chave = false;
				break;
			default:
				System.out.println("Número inválido\nDigite novamente!");
			}

		}
		return listaAntiga;
	}
	public static Map<Integer,Produto> operVenda(Map<Integer, Produto>lista, Map<Integer, Cliente>listaCliente){
		Scanner entrada = new Scanner(System.in);
		int x,y,qnt;
		System.out.println("---VENDAS---");
		System.out.println("Escolha o produto que gostaria de vender:");
		for(int p:lista.keySet()) {
			System.out.println(p+"   #### "+lista.get(p).nome);
		}
		x = entrada.nextInt();
		if(lista.containsKey(x)){
			System.out.println("\n---Lista Clientes---");
			for(Integer p:listaCliente.keySet()) {
			System.out.println(p+"   #### "+listaCliente.get(p).pessoaCliente.nome);
			}
			System.out.println("Selecione o codigo do Cliente:");
			y = entrada.nextInt();
			if(listaCliente.containsKey(y)) {
					System.out.println("Informe a quantidade:");
					qnt = entrada.nextInt();
					if(qnt<=lista.get(x).estoque) {
						lista.get(x).estoque -=qnt;
						System.out.println("--------------------------------------\n"
								+ "Venda de "+ qnt +" " + lista.get(x).nome +"(s) efetuada com sucesso!");
						System.out.println("Valor: R$" + (lista.get(x).precoVenda*(double)qnt)
				+ "\n--------------------------------------");
						Relatorio.vendas += (lista.get(x).precoVenda*(double)qnt);
						Relatorio.relatorioEscrito.add("Venda de "+qnt+"unid. de "+ lista.get(x).nome);
					}else {
						System.out.println("Quantidade em estoque insuficiente!");
					}			
			}else {
				System.out.println("Não existe cliente com esse codigo!");
			}
		}else{
			System.out.println("Codigo inexistente");
		}
		return lista;
	}
	public static Map<Integer,Produto> operRepor(Map<Integer,Produto>lista){
		Scanner entrada = new Scanner(System.in);
		System.out.println("Selecione o Codigo do Produto a Repor:");
		System.out.println("Cod #### Nome ###### Estoque ");
		for(int p:lista.keySet()) {
			System.out.println(p+"   #### "+lista.get(p).nome + " ### " + lista.get(p).estoque);
		}
		int x = entrada.nextInt();
		if(lista.containsKey(x)) {
			System.out.println("Digite a quantidade que voce quer adicionar de "+lista.get(x).nome);
			lista.get(x).estoque += entrada.nextInt();
			System.out.println("Nova quantidade adicionada com sucesso!\n"
					+ "Novo estoque de "+ lista.get(x).nome + " é: " + lista.get(x).estoque);
		}else {
			System.out.println("Codigo sem produto!");
		}
		
		return lista;
	}
	
	//Menu e Exibição
	public static void exibirRelatorio() {
		System.out.println("--------------------------------------\n"
				+ "RELATORIO DE VENDAS DA SESSÃO");
		System.out.println("VENDAS TOTAIS DO DIA: R$" + Relatorio.vendas);
		System.out.println("--------------------------------------\n"
				+ "VENDAS DETALHADAS");
		for(String s:Relatorio.relatorioEscrito) {
			System.out.println(s);
		}
	}
	public static int menuPrincipal() {
		Scanner entrada = new Scanner(System.in);
		System.out.println("####Menu Principal####\n");
		System.out.println("O que gostaria de fazer?\n1-Vender\n2-Lista de Produtos\n"
				+ "3-Menu Repositor\n4-Relatorio de Vendas do Dia\n5-Menu de Cadastro\n6-Encerrar");
		return entrada.nextInt();
	}
	public static int menuCadastro() {
		Scanner entrada = new Scanner(System.in);
		System.out.println("--------------------------------------\nMenu de Cadastros\n");
		System.out.println("1-Cadastro de Pessoas\n2-Cadastro de Clientes\n"
				+ "3-Cadastro de Empresas\n4-Cadastro de Fornecedor\n5-Cadastro de Caixa\n"
				+ "6-Cadastro de Repositor\n7-Operações de Produto\n8-Voltar ao Menu Principal"
				+ "\n--------------------------------------");
		return entrada.nextInt();
	}
	public static void exibirProdutos(Map<Integer, Produto>lista){
		int x = 0;
		Scanner entrada = new Scanner(System.in);
		System.out.println("\n###Lista Atual de Produtos###\n");
		System.out.println("Cod #### Nome\n");
		for(int p:lista.keySet()) {
			System.out.println(p+"   #### "+lista.get(p).nome);
		}
		do {
			System.out.println("\nDigite o codigo do produto para ver detalhadamente \nou 0 para continuar");
			x = entrada.nextInt();
			if (lista.containsKey(x)){
				System.out.println("--------------------------------------\n"
						+ "Nome:"+lista.get(x).nome);
				System.out.println("Descrição:"+lista.get(x).descricao);
				System.out.println("Validade::"+lista.get(x).dataFormatada());
				System.out.println("Fornecedor:"+lista.get(x).fornecedor);
				System.out.println("Preço de Compra:"+lista.get(x).precoCompra);
				System.out.println("Preco de Venda:"+lista.get(x).precoVenda);
				System.out.println("Estoque:"+lista.get(x).estoque);
				System.out.println("--------------------------------------");
			}else if(x==0){
				System.out.println("Voltando ao Menu\n--------------------------------------");
			}else{
				System.out.println("Codigo inexistente!\n");
			}
		} while (x!=0);
		//entrada.close();
	}	
	public static void exibirPessoas(Map<Integer, Pessoa>lista) {
		System.out.println("#Lista de Pessoas Cadastradas#");
		System.out.println("Cod #### Nome");
		for(int p:lista.keySet()) {
			System.out.println(p+"   #### "+lista.get(p).nome);
		}
	}
	public static void exibirPessoasDetalhes(Map<Integer, Pessoa>lista) {
		int x=0;
		Scanner entrada = new Scanner(System.in);
		System.out.println("###Lista Atual de Pessoas###\n");
		System.out.println("Cod #### Nome\n");
		for(int p:lista.keySet()) {
			System.out.println(p+"   #### "+lista.get(p).nome);
		}
		do {
			System.out.println("\nDigite o codigo da pessoa para ver detalhadamente \nou 0 para voltar"
					+ " ao menu inicial");
			x = entrada.nextInt();
			if (lista.containsKey(x)){
				System.out.println("Nome: "+lista.get(x).nome);
				System.out.println("Endereço: "+lista.get(x).endereco);
				System.out.println("CPF/CNPJ: "+lista.get(x).cpf);
				System.out.println("Telefone: "+lista.get(x).telefone);
			}else if(x==0){
				System.out.println("Voltando ao Menu Principal...");
			}else{
				System.out.println("Codigo inexistente!\n");
			}
		} while (x!=0);
		entrada.close();
	}
	//Metodos de Cadastro
	public static Map<Integer, Pessoa> cadastroPessoas(Map<Integer, Pessoa> lista){
		exibirPessoas(lista);
		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite o codigo da nova pessoa a cadastrar:");
		int x = Integer.parseInt(entrada.nextLine());;
		if(lista.containsKey(x)==false) {
			lista.put(x, new Pessoa());
			System.out.println("Digite o nome:");
			lista.get(x).nome = entrada.nextLine();
			System.out.println("Digite o endereço:");
			lista.get(x).endereco = entrada.nextLine();
			System.out.println("Digite o cpf/cnpj:");
			lista.get(x).cpf = entrada.nextLong();
			System.out.println("Digite o Telefone:");
			lista.get(x).telefone = entrada.nextLong();
			System.out.println("Pessoa cadastrada com sucesso!");
		}else {
			System.out.println("Codigo já utilizado!\n");
		}
		return lista;
	}
	public static Map<Integer, Caixa> cadastroCaixa(Map<Integer, Caixa> lista,Map<Integer, Pessoa> listaPessoas){
		Scanner entrada = new Scanner(System.in);
		int x;
		System.out.println("--Lista Atual de Caixas--");
		if(lista.size()==0) {
			System.out.println("Sem Caixas Cadastrados\n");
		}else {
			System.out.println("Cod #### Nome");
			for(int p:lista.keySet()) {
				System.out.println(p+"   #### "+lista.get(p).pessoaCaixa.nome);
			}
		}
		System.out.println("Voce gostaria de?\n1-Cadastrar Caixas\n2-Excluir Caixas\n3-Alterar Caixas");
		int num = entrada.nextInt();
		switch (num) {
		case 1:
			System.out.println("Digite o codigo do novo caixa a cadastrar:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)==false) {
				System.out.println("Escolha uma pessoa que você quer atribuir como caixa:");
				exibirPessoas(listaPessoas);
				System.out.println("Digite o codigo da pessoa:");
				lista.put(x, new Caixa());
				int y = Integer.parseInt(entrada.next());
				lista.get(x).pessoaCaixa = listaPessoas.get(y);
				System.out.println("Digite o numero da Carteira de Trabalho");
				lista.get(x).clt = entrada.nextInt();
				System.out.println("Caixa Cadastrado com Sucesso!/nVoltando a Menu Principal!...");
			}else {
				System.out.println("Codigo ja utilizado!");
			}
			break;
		case 2:
			System.out.println("Digite o Codigo do caixa a excluir:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)) {
				System.out.printf("Caixa %s excluido com sucesso!\n", lista.remove(x).pessoaCaixa.nome);
			}else {
				System.out.println("Esse codigo não existe");
			}
			break;
		case 3:
			System.out.println("Digite o codigo do caixa que deseja alterar:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)) {
				System.out.println("Escolha uma nova pessoa que você quer atribuir como caixa:");
				exibirPessoas(listaPessoas);
				System.out.println("Digite o codigo da pessoa:");
				int y = Integer.parseInt(entrada.next());
				lista.get(x).pessoaCaixa = listaPessoas.get(y);
				System.out.println("Digite o numero da Carteira de Trabalho");
				lista.get(x).clt = entrada.nextInt();
				System.out.println("Caixa atualizado com Sucesso!/Voltando a Menu Principal!...");
			}else {
				System.out.println("Esse codigo não existe");
			}
			break;
		default:
			System.out.println("Número inválido");
		}
		return lista;
	}
	public static Map<Integer, Cliente> cadastroCliente(Map<Integer, Cliente> lista,Map<Integer, Pessoa> listaPessoas){
		Scanner entrada = new Scanner(System.in);
		int x;
		System.out.println("--Lista Atual de Clientes--");
		if(lista.size()==0) {
			System.out.println("Sem Clientes Cadastrados\n");
		}else {
			System.out.println("Cod #### Nome");
			for(int p:lista.keySet()) {
				System.out.println(p+"   #### "+lista.get(p).pessoaCliente.nome);
			}
		}
		System.out.println("Voce gostaria de?\n1-Cadastrar Clientes\n2-Excluir Cliente\n3-Alterar Cliente");
		int num = Integer.parseInt(entrada.next());
		switch (num) {
		case 1:
			System.out.println("Digite o codigo do novo cliente a cadastrar:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)==false) {
				System.out.println("Escolha uma pessoa que você quer atribuir como cliente:");
				exibirPessoas(listaPessoas);
				System.out.println("Digite o codigo da pessoa:");
				lista.put(x, new Cliente());
				int y = Integer.parseInt(entrada.next());
				lista.get(x).pessoaCliente = listaPessoas.get(y);
				System.out.println("Digite a categoria da pessoa: (PJ ou PF)");
				lista.get(x).categoria = entrada.next();
				System.out.println("Cliente Cadastrado com Sucesso!\nRetornando ao Menu Principal..");
			}else {
				System.out.println("Codigo ja utilizado!");
			}
			break;
		case 2:
			System.out.println("Digite o codigo do cliente a excluir:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)) {
				System.out.printf("Cliente %s excluido com sucesso!\n", lista.remove(x).pessoaCliente.nome);
			}else {
				System.out.println("Esse codigo não existe");
			}
			break;
		case 3:
			System.out.println("Digite o codigo do novo cliente a cadastrar:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)) {
				System.out.println("Escolha uma pessoa que você quer atribuir como novo cliente:");
				exibirPessoas(listaPessoas);
				System.out.println("Digite o codigo da pessoa:");
				int y = Integer.parseInt(entrada.next());
				lista.get(x).pessoaCliente = listaPessoas.get(y);
				System.out.println("Digite a categoria da pessoa: (PJ ou PF)");
				lista.get(x).categoria = entrada.next();
				System.out.println("Cliente Atualizado com Sucesso!\nRetornando ao Menu Principal..");
			}else {
				System.out.println("Codigo não existe!");
			}
			break;
		default:
			System.out.println("Número inválido");
		}

		return lista;
	}
	public static Map<Integer, Empresa> cadastroEmpresa(Map<Integer, Empresa> lista,Map<Integer, Pessoa> listaPessoas){
		Scanner entrada = new Scanner(System.in);
		int x;
		System.out.println("--Lista Atual de Empresas--");
		if(lista.size()==0) {
			System.out.println("Sem Empresas Cadastradas\n");
		}else{
			System.out.println("Cod #### Nome");
			for(int p:lista.keySet()) {
				System.out.println(p+"   #### "+lista.get(p).nomeFantasia);
			}
		}
		System.out.println("Voce gostaria de?\n1-Cadastrar Empresa\n2-Excluir Empresa\n3-Alterar Empresa");
		int num = entrada.nextInt();
		switch (num) {
		case 1:
			System.out.println("Digite o codigo da nova empresa a cadastrar:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)==false) {
				System.out.println("Escolha uma pessoa que você quer atribuir \ncomo empresário responsavel:");
				exibirPessoas(listaPessoas);
				System.out.println("Digite o codigo da pessoa:");
				lista.put(x, new Empresa());
				int y = Integer.parseInt(entrada.next());
				lista.get(x).empresario = listaPessoas.get(y);
				System.out.println("Digite o nome fantasia da empresa:");
				lista.get(x).nomeFantasia = entrada.next();
				System.out.println("Empresa Cadastrada com Sucesso!\nRetornando ao Menu Principal..");
			}else {
				System.out.println("Codigo ja utilizado!");
			}
			break;
		case 2:
			System.out.println("Digite o codigo da empresa a excluir:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)) {
				System.out.printf("Empresa %s excluido com sucesso!\n", lista.remove(x).nomeFantasia);
			}else {
				System.out.println("Esse codigo não existe");
			}
			break;
		case 3:
			System.out.println("Digite o codigo da nova empresa a alterar:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)) {
				System.out.println("Escolha uma nova pessoa que você quer atribuir como empresário responsavel:");
				exibirPessoas(listaPessoas);
				System.out.println("Digite o codigo da pessoa:");
				int y = Integer.parseInt(entrada.next());
				lista.get(x).empresario = listaPessoas.get(y);
				System.out.println("Digite o nome fantasia da empresa:");
				lista.get(x).nomeFantasia = entrada.next();
				System.out.println("Empresa autualizada com Sucesso!\nRetornando ao Menu Principal..");
			}else {
				System.out.println("Codigo ja utilizado!");
			}
			break;
		default:
			System.out.println("Número inválido");
		}

		return lista;
	}
	public static Map<Integer, Fornecedor> cadastroFornecedor(Map<Integer, Fornecedor> lista,Map<Integer, Pessoa> listaPessoas,Map<Integer, Empresa> listaEmpresa){
		Scanner entrada = new Scanner(System.in);
		int x,y;
		System.out.println("--Lista Atual de Fornecedores--");
		if(lista.size()==0) {
			System.out.println("Sem Fornecedores Cadastrados\n");
		}else{
			System.out.println("Cod #### Pessoa Representante ######## Empresa");
			for(int p:lista.keySet()) {
				System.out.println(p+"   #### "+lista.get(p).pessoaFornecedor.nome+" ######## " + lista.get(p).empresaFornecedor.nomeFantasia);
			}
		}
		if(listaEmpresa.size() == 0) {
			System.out.println("Sem Empresas cadastradas\nPrimeiro cadastre uma empresa"
					+ "para cadastrar um fornecedor!");
			return lista;
		}
		System.out.println("\nVoce gostaria de?\n1-Cadastrar Fornecedor\n2-Excluir Fornecedor\n3-Alterar Fornecedor");
		int num = entrada.nextInt();
		switch (num) {
		case 1:
			System.out.println("Digite um codigo do fornecedor para cadastrar:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)==false) {
				System.out.println("Escolha uma pessoa que você quer atribuir como fornecedor:");
				exibirPessoas(listaPessoas);
				System.out.println("Digite o codigo da pessoa:");
				lista.put(x, new Fornecedor());
				y = Integer.parseInt(entrada.next());
				lista.get(x).pessoaFornecedor = listaPessoas.get(y);
				System.out.println("---LISTA EMPRESAS---");
				for(int p:listaEmpresa.keySet()) {
					System.out.println(p+"   #### "+listaEmpresa.get(p).nomeFantasia);
				}
				System.out.println("Digite o codigo da empresa do fornecedor:");
				y = Integer.parseInt(entrada.next());
				lista.get(x).empresaFornecedor = listaEmpresa.get(y);
				System.out.println("Fornecedor Cadastrado com Sucesso!\nRetornando ao Menu Principal..\n");
				return lista;
			}else {
				System.out.println("Codigo ja utilizado!");
				return lista;
			}
		case 2:
			System.out.println("Digite o codigo do fornecedor a excluir:*");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)) {
				System.out.printf("Fornecedor %s excluido com sucesso!\n", lista.remove(x).pessoaFornecedor.nome);
				return lista;
			}else {
				System.out.println("Esse codigo não existe");
				return lista;
			}
		case 3:
			System.out.println("Digite um codigo do fornecedor para atualizar:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)) {
				System.out.println("Escolha uma nova pessoa que você quer atribuir como fornecedor:");
				exibirPessoas(listaPessoas);
				System.out.println("Digite o codigo da pessoa:");
				y = Integer.parseInt(entrada.next());
				lista.get(x).pessoaFornecedor = listaPessoas.get(y); 
				for(int p:listaEmpresa.keySet()) {
					System.out.println(p+"   #### "+listaEmpresa.get(p).nomeFantasia);
				}
				System.out.println("Digite o codigo da empresa do fornecedor:");
				y = Integer.parseInt(entrada.next());
				lista.get(x).empresaFornecedor = listaEmpresa.get(y);
				System.out.println("Fornecedor atualizado com Sucesso!\nRetornando ao Menu Principal..");
				return lista;
			}else {
				System.out.println("Codigo ja utilizado!");
				return lista;
			}
		default:
			System.out.println("Número inválido");
		}
		return lista;
	}
	public static Map<Integer, Repositor> cadastroRepositor(Map<Integer, Repositor> lista,Map<Integer, Pessoa> listaPessoas){
		Scanner entrada = new Scanner(System.in);
		int x;
		System.out.println("--Lista Atual de Repositor--");
		if(lista.size()==0) {
			System.out.println("Sem Repositores Cadastrados\n");
		}else {
			System.out.println("Cod #### Nome");
			for(int p:lista.keySet()) {
				System.out.println(p+"   #### "+lista.get(p).pessoaRepositor.nome);
			}
		}
		System.out.println("Voce gostaria de?\n1-Cadastrar Repositor\n2-Excluir Repositor\n3-Alterar Repositor");
		int num = entrada.nextInt();
		switch (num) {
		case 1:
			System.out.println("Digite o codigo do novo repositor para cadastrar:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)==false) {
				System.out.println("Escolha uma pessoa que você quer atribuir como repositor:");
				exibirPessoas(listaPessoas);
				System.out.println("Digite o codigo da pessoa:");
				lista.put(x, new Repositor());
				int y = Integer.parseInt(entrada.next());
				lista.get(x).pessoaRepositor = listaPessoas.get(y);
				System.out.println("Digite o numero da Carteira de Trabalho");
				lista.get(x).clt = entrada.nextInt();
				System.out.println("Repositor Cadastrado com Sucesso!/Voltando a Menu Principal!...");
			}else {
				System.out.println("Codigo ja utilizado!\nVoltando ao Menu Principal...");
			}
			break;
		case 2:
			System.out.println("Digite o codigo do repositor a excluir:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)) {
				System.out.printf("Repositor %s excluido com sucesso!\n", lista.remove(x).pessoaRepositor.nome);
			}else {
				System.out.println("Esse codigo não existe");
			}
			break;
		case 3:
			System.out.println("Digite o codigo do novo repositor para alterar:");
			x = Integer.parseInt(entrada.next());;
			if(lista.containsKey(x)==false) {
				System.out.println("Escolha uma pessoa que você quer atribuir como repositor:");
				exibirPessoas(listaPessoas);
				System.out.println("Digite o codigo da pessoa:");
				int y = Integer.parseInt(entrada.next());
				lista.get(x).pessoaRepositor = listaPessoas.get(y);
				System.out.println("Digite o numero da Carteira de Trabalho:");
				lista.get(x).clt = entrada.nextInt();
				System.out.println("Repositor atualizado com Sucesso!\nVoltando a Menu Principal!...");
			}else {
				System.out.println("Codigo ja utilizado!\nVoltando ao Menu Principal...");
			}
			break;
		default:
			System.out.println("Número inválido\nVoltando ao Menu Principal...");
			
		}
		return lista;
	}

}
