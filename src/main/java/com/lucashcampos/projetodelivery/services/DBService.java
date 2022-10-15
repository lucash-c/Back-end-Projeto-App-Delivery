package com.lucashcampos.projetodelivery.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.lucashcampos.projetodelivery.domain.Pizza;
import com.lucashcampos.projetodelivery.domain.PizzaAdicional;
import com.lucashcampos.projetodelivery.domain.PizzaMassa;
import com.lucashcampos.projetodelivery.domain.PizzaSaborTamanho;
import com.lucashcampos.projetodelivery.domain.Produto;
import com.lucashcampos.projetodelivery.domain.enums.EstadoPagamento;
import com.lucashcampos.projetodelivery.domain.enums.Perfil;
import com.lucashcampos.projetodelivery.domain.enums.TipoCliente;
import com.lucashcampos.projetodelivery.repositories.CategoriaRepository;
import com.lucashcampos.projetodelivery.repositories.CidadeRepository;
import com.lucashcampos.projetodelivery.repositories.ClienteRepository;
import com.lucashcampos.projetodelivery.repositories.EnderecoRepository;
import com.lucashcampos.projetodelivery.repositories.EstadoRepository;
import com.lucashcampos.projetodelivery.repositories.ItemPedidoRepository;
import com.lucashcampos.projetodelivery.repositories.PagamentoRepository;
import com.lucashcampos.projetodelivery.repositories.PedidoRepository;
import com.lucashcampos.projetodelivery.repositories.PizzaAdicionalRepository;
import com.lucashcampos.projetodelivery.repositories.PizzaMassaRepository;
import com.lucashcampos.projetodelivery.repositories.PizzaSaborTamanhoRepository;
import com.lucashcampos.projetodelivery.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;

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
	private PizzaSaborTamanhoRepository pizzaSaborTamanhoRepository;

	@Autowired
	private PizzaMassaRepository pizzaMassaRepository;

	@Autowired
	private PizzaAdicionalRepository pizzaAdicionalRepository;

	public void InstantiateTestDatabase() throws ParseException {
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
		PizzaSaborTamanho PortuguesaGrande = new PizzaSaborTamanho(null, "Portuguesa",
				"Molho, Mussarela, Tomate, presunto, ovo, cebola e orégano", "Grande", 8, 4, 35.00);
		PizzaSaborTamanho MussarelaGrande = new PizzaSaborTamanho(null, "Mussarela",
				"Molho, Mussarela, Tomate e orégano", "Grande", 8, 4, 30.00);
		PizzaSaborTamanho MussarelaMedia = new PizzaSaborTamanho(null, "Mussarela",
				"Molho, Mussarela, Tomate e orégano", "Media", 6, 3, 26.00);
		PizzaSaborTamanho MussarelaBroto = new PizzaSaborTamanho(null, "Mussarela",
				"Molho, Mussarela, Tomate e orégano", "Broto", 2, 1, 22.00);

		pizzaSaborTamanhoRepository
				.saveAll(Arrays.asList(MussarelaGrande, MussarelaMedia, MussarelaBroto, PortuguesaGrande));

		PizzaMassa bordaSimples = new PizzaMassa(null, "Borda simples", 2.00);
		pizzaMassaRepository.saveAll(Arrays.asList(bordaSimples));

		PizzaAdicional ad1 = new PizzaAdicional(null, "Calabresa", 6.00);
		pizzaAdicionalRepository.saveAll(Arrays.asList(ad1));

		Pizza p4 = new Pizza(Arrays.asList(MussarelaGrande), bordaSimples, Arrays.asList(ad1), "tirar tomate");
		Pizza p12 = new Pizza(Arrays.asList(MussarelaGrande, PortuguesaGrande), bordaSimples, Arrays.asList(ad1),
				"tirar cebola");

		Produto p13 = new Produto(null, "Produto 13", 10.00);
		Produto p14 = new Produto(null, "Produto 14", 10.00);
		Produto p15 = new Produto(null, "Produto 15", 10.00);
		Produto p16 = new Produto(null, "Produto 16", 10.00);
		Produto p17 = new Produto(null, "Produto 17", 10.00);
		Produto p18 = new Produto(null, "Produto 18", 10.00);
		Produto p19 = new Produto(null, "Produto 19", 10.00);
		Produto p20 = new Produto(null, "Produto 20", 10.00);
		Produto p21 = new Produto(null, "Produto 21", 10.00);
		Produto p22 = new Produto(null, "Produto 22", 10.00);
		Produto p23 = new Produto(null, "Produto 23", 10.00);
		Produto p24 = new Produto(null, "Produto 24", 10.00);
		Produto p25 = new Produto(null, "Produto 25", 10.00);
		Produto p26 = new Produto(null, "Produto 26", 10.00);
		Produto p27 = new Produto(null, "Produto 27", 10.00);
		Produto p28 = new Produto(null, "Produto 28", 10.00);
		Produto p29 = new Produto(null, "Produto 29", 10.00);
		Produto p30 = new Produto(null, "Produto 30", 10.00);
		Produto p31 = new Produto(null, "Produto 31", 10.00);
		Produto p32 = new Produto(null, "Produto 32", 10.00);
		Produto p33 = new Produto(null, "Produto 33", 10.00);
		Produto p34 = new Produto(null, "Produto 34", 10.00);
		Produto p35 = new Produto(null, "Produto 35", 10.00);
		Produto p36 = new Produto(null, "Produto 36", 10.00);
		Produto p37 = new Produto(null, "Produto 37", 10.00);
		Produto p38 = new Produto(null, "Produto 38", 10.00);
		Produto p39 = new Produto(null, "Produto 39", 10.00);
		Produto p40 = new Produto(null, "Produto 40", 10.00);
		Produto p41 = new Produto(null, "Produto 41", 10.00);
		Produto p42 = new Produto(null, "Produto 42", 10.00);
		Produto p43 = new Produto(null, "Produto 43", 10.00);
		Produto p44 = new Produto(null, "Produto 44", 10.00);
		Produto p45 = new Produto(null, "Produto 45", 10.00);
		Produto p46 = new Produto(null, "Produto 46", 10.00);
		Produto p47 = new Produto(null, "Produto 47", 10.00);
		Produto p48 = new Produto(null, "Produto 48", 10.00);
		Produto p49 = new Produto(null, "Produto 49", 10.00);
		Produto p50 = new Produto(null, "Produto 50", 10.00);
		cat1.getProdutos()
				.addAll(Arrays.asList(p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28,
						p29, p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48,
						p49, p50));

		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);
		p47.getCategorias().add(cat1);
		p48.getCategorias().add(cat1);
		p49.getCategorias().add(cat1);
		p50.getCategorias().add(cat1);

		cat1.getProdutos().addAll(Arrays.asList(p3, p5));
		cat2.getProdutos().addAll(Arrays.asList(p1, p2, p6, p8));
		cat3.getProdutos().addAll(Arrays.asList(p4, p12));
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
		p12.getCategorias().addAll(Arrays.asList(cat3));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));
		
		produtoRepository.saveAll(
				Arrays.asList(p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30,
						p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "56198765545", "Maria Silva", "lucash_c@yahoo.com.br",
				TipoCliente.PESSOAFISICA, pe.encode("1234"));
		cli1.getTelefones().addAll(Arrays.asList("5465465645", "56116161"));

		Cliente cli2 = new Cliente(null, "39140661059", "Rafaela Maelyse", "rafaelamaelyse@outlook.com",
				TipoCliente.PESSOAFISICA, pe.encode("1234"));
		cli2.getTelefones().addAll(Arrays.asList("55555454", "1111555555"));
		cli2.addPerfil(Perfil.ADMIN);

		Endereco e1 = new Endereco(null, "Rua das Flores", "300", "Apto 303", "Jardim", "38220834", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "5465465", c2, cli1);
		Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "8858585", c2, cli2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

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

		ItemPedido ip1 = new ItemPedido(p1, ped1, 0.00, 1, p1.getPreco());
		ItemPedido ip2 = new ItemPedido(p3, ped1, 0.00, 2, p3.getPreco());
		ItemPedido ip3 = new ItemPedido(p2, ped2, 0.00, 1, p2.getPreco());
		ItemPedido ip4 = new ItemPedido(p4, ped1, 0.00, 1, p4.getPreco());

		ped1.getItens().addAll(Arrays.asList(ip1, ip2, ip4));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1, ip4));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		p4.getItens().addAll(Arrays.asList(ip1));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3, ip4));
	}
}
