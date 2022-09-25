package com.lucashcampos.projetodelivery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucashcampos.projetodelivery.domain.Categoria;
import com.lucashcampos.projetodelivery.domain.Cidade;
import com.lucashcampos.projetodelivery.domain.Cliente;
import com.lucashcampos.projetodelivery.domain.Endereco;
import com.lucashcampos.projetodelivery.domain.Estado;
import com.lucashcampos.projetodelivery.domain.ItemPedido;
import com.lucashcampos.projetodelivery.domain.Pagamento;
import com.lucashcampos.projetodelivery.domain.PagamentoAVista;
import com.lucashcampos.projetodelivery.domain.PagamentoComCartao;
import com.lucashcampos.projetodelivery.domain.Pedido;
import com.lucashcampos.projetodelivery.domain.Produto;
import com.lucashcampos.projetodelivery.domain.enums.EstadoPagamento;
import com.lucashcampos.projetodelivery.domain.enums.TipoCliente;
import com.lucashcampos.projetodelivery.domain.pizza.Pizza;
import com.lucashcampos.projetodelivery.domain.pizza.PizzaAdicional;
import com.lucashcampos.projetodelivery.domain.pizza.PizzaMassa;
import com.lucashcampos.projetodelivery.domain.pizza.PizzaTamanho;
import com.lucashcampos.projetodelivery.repositories.CategoriaRepository;
import com.lucashcampos.projetodelivery.repositories.CidadeRepository;
import com.lucashcampos.projetodelivery.repositories.ClienteRepository;
import com.lucashcampos.projetodelivery.repositories.EnderecoRepository;
import com.lucashcampos.projetodelivery.repositories.EstadoRepository;
import com.lucashcampos.projetodelivery.repositories.ItemPedidoRepository;
import com.lucashcampos.projetodelivery.repositories.PagamentoRepository;
import com.lucashcampos.projetodelivery.repositories.PedidoRepository;
import com.lucashcampos.projetodelivery.repositories.ProdutoRepository;
import com.lucashcampos.projetodelivery.repositories.pizzas.PizzaAdicionalRepository;
import com.lucashcampos.projetodelivery.repositories.pizzas.PizzaMassaRepository;
import com.lucashcampos.projetodelivery.repositories.pizzas.PizzaTamanhoRepository;

@SpringBootApplication
public class ProjetodeliveryApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private PizzaTamanhoRepository pizzaTamanhoRepository;

	@Autowired
	private PizzaMassaRepository pizzaMassaRepository;

	@Autowired
	private PizzaAdicionalRepository pizzaAdicionalRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetodeliveryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Lanches");
		Categoria cat2 = new Categoria(null, "Bebidas");
		Categoria cat3 = new Categoria(null, "Pizzas");
		Categoria cat4 = new Categoria(null, "Doces");
		Categoria cat5 = new Categoria(null, "Pastéis");
		Categoria cat6 = new Categoria(null, "Mercado");
		Categoria cat7 = new Categoria(null, "Farmacia");

		Produto p1 = new Produto(null, "Coca-cola 2l", 15.00);
		Produto p2 = new Produto(null, "Suco de laranja", 8.00);
		Produto p3 = new Produto(null, "X-Bacon", 20.00);
		Produto p5 = new Produto(null, "X-Egg", 18.00);
		Produto p6 = new Produto(null, "Suco de Morango", 20.00);
		Produto p7 = new Produto(null, "Pastel de Frango", 10.00);
		Produto p8 = new Produto(null, "Milk Shake Ovomaltine", 9.00);
		Produto p9 = new Produto(null, "Bolo no pote", 6.00);
		Produto p10 = new Produto(null, "Pastel de queijo", 20.00);
		Produto p11 = new Produto(null, "Dipirona", 6.00);

		// pizza
		PizzaTamanho grande = new PizzaTamanho(null, "Grande", 8, 4);
		PizzaTamanho media = new PizzaTamanho(null, "Media", 6, 2);
		PizzaTamanho broto = new PizzaTamanho(null, "Broto", 4, 1);
		pizzaTamanhoRepository.saveAll(Arrays.asList(grande, media, broto));

		PizzaMassa bordaSimples = new PizzaMassa(null, "Borda simples", 2.00);
		pizzaMassaRepository.saveAll(Arrays.asList(bordaSimples));

		PizzaAdicional ad1 = new PizzaAdicional(null, "Calabresa", 6.00);
		pizzaAdicionalRepository.saveAll(Arrays.asList(ad1));

		Pizza p4 = new Pizza("Pizza Mussarela", grande, "Molho, Mussarela, Tomate e orégano", 30.00);

		cat1.getProdutos().addAll(Arrays.asList(p3, p5));
		cat2.getProdutos().addAll(Arrays.asList(p1, p2, p6, p8));
		cat3.getProdutos().addAll(Arrays.asList(p4));
		cat4.getProdutos().addAll(Arrays.asList(p8, p9));
		cat5.getProdutos().addAll(Arrays.asList(p7, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat2));
		p2.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat3));
		p5.getCategorias().addAll(Arrays.asList(cat1));
		p6.getCategorias().addAll(Arrays.asList(cat2));
		p7.getCategorias().addAll(Arrays.asList(cat5));
		p8.getCategorias().addAll(Arrays.asList(cat2, cat4));
		p9.getCategorias().addAll(Arrays.asList(cat4));
		p10.getCategorias().addAll(Arrays.asList(cat5));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "56198765545", "Maria Silva", "maria@gmail.com", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("5465465645", "56116161"));

		Endereco e1 = new Endereco(null, "Rua das Flores", "300", "Apto 303", "Jardim", "38220834", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "5465465", c2, cli1);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("10/09/2022 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2022 10:50"), cli1, e2);

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);

		Pagamento pgto2 = new PagamentoAVista(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2022 00:00"));
		ped2.setPagamento(pgto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));

		ItemPedido ip1 = new ItemPedido(p1, ped1, 0.00, 1.00, 15.00);
		ItemPedido ip2 = new ItemPedido(p3, ped1, 0.00, 2.00, 20.00);
		ItemPedido ip3 = new ItemPedido(p2, ped2, 0.00, 1.00, 8.00);

		Double totalPizza = p4.getPreco();

		PizzaMassa pizzaMassa = bordaSimples;
		totalPizza += pizzaMassa.getPreco();

		List<PizzaAdicional> adicionais = new ArrayList<>();
		adicionais = Arrays.asList(ad1);

		for (PizzaAdicional adicional : adicionais) {
			totalPizza += adicional.getPreco();
		}

		ItemPedido ip4 = new ItemPedido(p4, ped1, 0.00, 1.00, totalPizza);
		ip4.setAdicionais(adicionais);
		ip4.setMassa(pizzaMassa);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2, ip4));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1, ip4));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		p4.getItens().addAll(Arrays.asList(ip1));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3, ip4));

	}

}
