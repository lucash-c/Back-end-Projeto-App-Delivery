package com.lucashcampos.projetodelivery.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.Adicional;
import com.lucashcampos.projetodelivery.domain.Categoria;
import com.lucashcampos.projetodelivery.domain.Endereco;
import com.lucashcampos.projetodelivery.domain.Frete;
import com.lucashcampos.projetodelivery.domain.ItemPedido;
import com.lucashcampos.projetodelivery.domain.Loja;
import com.lucashcampos.projetodelivery.domain.Pagamento;
import com.lucashcampos.projetodelivery.domain.PagamentoComCartao;
import com.lucashcampos.projetodelivery.domain.Pedido;
import com.lucashcampos.projetodelivery.domain.Pizza;
import com.lucashcampos.projetodelivery.domain.PizzaMassa;
import com.lucashcampos.projetodelivery.domain.PizzaSaborTamanho;
import com.lucashcampos.projetodelivery.domain.Produto;
import com.lucashcampos.projetodelivery.domain.Sorvete;
import com.lucashcampos.projetodelivery.domain.SorveteCobertura;
import com.lucashcampos.projetodelivery.domain.SorveteSabor;
import com.lucashcampos.projetodelivery.domain.SorveteTamanho;
import com.lucashcampos.projetodelivery.domain.Usuario;
import com.lucashcampos.projetodelivery.domain.enums.EspecialidadeLoja;
import com.lucashcampos.projetodelivery.domain.enums.EstadoPagamento;
import com.lucashcampos.projetodelivery.domain.enums.Perfil;
import com.lucashcampos.projetodelivery.domain.enums.TipoAdicional;
import com.lucashcampos.projetodelivery.domain.enums.TipoCliente;
import com.lucashcampos.projetodelivery.domain.enums.TipoProduto;
import com.lucashcampos.projetodelivery.repositories.AdicionalRepository;
import com.lucashcampos.projetodelivery.repositories.CategoriaRepository;
import com.lucashcampos.projetodelivery.repositories.EnderecoRepository;
import com.lucashcampos.projetodelivery.repositories.FreteRepository;
import com.lucashcampos.projetodelivery.repositories.ItemPedidoRepository;
import com.lucashcampos.projetodelivery.repositories.LojaRepository;
import com.lucashcampos.projetodelivery.repositories.PagamentoRepository;
import com.lucashcampos.projetodelivery.repositories.PedidoRepository;
import com.lucashcampos.projetodelivery.repositories.PizzaMassaRepository;
import com.lucashcampos.projetodelivery.repositories.PizzaSaborTamanhoRepository;
import com.lucashcampos.projetodelivery.repositories.ProdutoRepository;
import com.lucashcampos.projetodelivery.repositories.SorveteCoberturaRepository;
import com.lucashcampos.projetodelivery.repositories.SorveteRepository;
import com.lucashcampos.projetodelivery.repositories.SorveteSaborRepository;
import com.lucashcampos.projetodelivery.repositories.SorveteTamanhoRepository;
import com.lucashcampos.projetodelivery.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

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
	private AdicionalRepository adicionalRepository;

	@Autowired
	private SorveteCoberturaRepository sorveteCoberturaRepository;

	@Autowired
	private SorveteSaborRepository sorveteSaborRepository;

	@Autowired
	private SorveteTamanhoRepository sorveteTamanhoRepository;

	@Autowired
	private SorveteRepository sorveteRepository;

	@Autowired
	private LojaRepository lojaRepository;

	@Autowired
	private LojaService lojaService;

	@Autowired
	private FreteRepository freteRepository;

	@Transactional
	public void InstantiateTestDatabase() throws ParseException {		
		

		Usuario cli1 = new Usuario(null, "41516198875", "Lucas Campos", "lucash_c@yahoo.com.br",
				TipoCliente.PESSOAFISICA, "Básico", 5, "Cartão de Crédito", "Cliente antigo", true, true,
				pe.encode("1234"), "19991414411", "1936049510", Set.of(Perfil.CLIENTE, Perfil.ADMIN));

		Usuario cli2 = new Usuario(null, "987.654.321-00", "Maria Augusta", "maria.souza@example.com",
				TipoCliente.PESSOAFISICA, "Premium", 10, "Boleto", "Cliente VIP", true, true, pe.encode("senha456"),
				"19993446522","",
				Set.of(Perfil.CLIENTE));

		Endereco e1 = new Endereco(null, "Rua das Flores", "300", "Apto 303", "Centro", "São Paulo", "SP", "38220-834");

		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Bela Vista", "Rio de Janeiro", "RJ",
				"54654-655");

		Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "Curitiba", "PR", "88585-855");

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().add(e3);				

		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		usuarioRepository.saveAll(Arrays.asList(cli1, cli2));
		// Restaurante 1
		Loja r1 = new Loja("Delícias do Chef", "12345678000191", "Carlos Eduardo", "Sabor & Arte",
				List.of(EspecialidadeLoja.PIZZARIA.getCod(), EspecialidadeLoja.BRASILEIRA.getCod()),
				new Endereco(null, "Rua das Flores", "10", "Loja 1", "01001-000", "Centro", "São Paulo", "SP"),
				"(11) 1234-5678", "(11) 98765-4321", 4.8, "logo1.jpg", "www.saborearte.com.br", "saborearte_instagram",
				"saborearte_facebook", 30, 10, new ArrayList<>(), new ArrayList<>(List.of(cli1)));

		// Restaurante 2
		Loja r2 = new Loja("Casa do Sushi", "98765432100056", "Fernanda Oliveira", "Sushi Master",
				List.of(EspecialidadeLoja.JAPONESA.getCod()),
				new Endereco(null, "Av. Paulista", "1500", "Sala 10", "01310-100", "Bela Vista", "São Paulo", "SP"),
				"(11) 3333-4444", "(11) 99876-5432", 4.6, "logo2.jpg", "www.sushimaster.com.br",
				"sushimaster_instagram", "sushimaster_facebook", 25, 15, new ArrayList<>(),
				new ArrayList<>(List.of(cli2)));

		// Restaurante 3
		Loja r3 = new Loja("Cozinha Mineira", "11223344556678", "Roberto Lima", "Sabores de Minas",
				List.of(EspecialidadeLoja.BRASILEIRA.getCod()),
				new Endereco(null, "Rua das Palmeiras", "100", "Casa", "30140-001", "Savassi", "Belo Horizonte", "MG"),
				"(31) 4444-5555", "(31) 98765-4321", 4.7, "logo3.jpg", "www.saboresdeminas.com.br",
				"saboresdeminas_instagram", "saboresdeminas_facebook", 35, 12, new ArrayList<>(),
				new ArrayList<>(List.of(cli2)));

		// Restaurante 4
		Loja r4 = new Loja("Churrasco Gaúcho", "99887766554434", "Mariana Sousa", "Gaúcho Grill",
				List.of(EspecialidadeLoja.BRASILEIRA.getCod()),
				new Endereco(null, "Av. Brasil", "2500", "Sala 5", "90230-060", "Centro", "Porto Alegre", "RS"),
				"(51) 5555-6666", "(51) 98765-1234", 4.9, "logo4.jpg", "www.gauchogrill.com.br",
				"gauchogrill_instagram", "gauchogrill_facebook", 40, 20, new ArrayList<>(),
				new ArrayList<>(List.of(cli1)));

		// Restaurante 5
		Loja r5 = new Loja("Pastelaria do João", "12312312300012", "João Alves", "Pastel do João",
				List.of(EspecialidadeLoja.BRASILEIRA.getCod()),
				new Endereco(null, "Rua Central", "500", "Box 8", "80010-000", "Centro", "Curitiba", "PR"),
				"(41) 7777-8888", "(41) 98765-9876", 4.5, "logo5.jpg", "www.pasteldojoao.com.br",
				"pasteldojoao_instagram", "pasteldojoao_facebook", 20, 8, new ArrayList<>(),
				new ArrayList<>(List.of(cli2)));

		cli1.getLojas().addAll(Arrays.asList(r1, r4));
		cli2.getLojas().addAll(Arrays.asList(r2, r3, r5));

		lojaRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5));
		usuarioRepository.saveAll(Arrays.asList(cli1, cli2));

		Frete f1 = new Frete(null, 2, 2.0, 30, r1);
		Frete f2 = new Frete(null, 3, 3.0, 40, r1);
		Frete f3 = new Frete(null, 2, 2.5, 40, r2);
		Frete f4 = new Frete(null, 4, 4.0, 60, r2);
		Frete f5 = new Frete(null, 2, 2.0, 30, r3);

		freteRepository.saveAll(Arrays.asList(f1, f2, f3, f4, f5));
		Frete f6 = new Frete(15, 20, 10.0, 120, r3);
		r3.getFretes().add(f6);
		lojaService.update(r3);

		Categoria cat1 = new Categoria(null, "Lanches", r1, true);
		Categoria cat2 = new Categoria(null, "Bebidas", r1, true);
		Categoria cat3 = new Categoria(null, "Pizzas", r2, true);
		Categoria cat4 = new Categoria(null, "Doces", r1, true);
		Categoria cat5 = new Categoria(null, "Pastéis", r3, true);
		Categoria cat6 = new Categoria(null, "Mercado", r4, true);
		Categoria cat7 = new Categoria(null, "Farmacia", r5, true);

		Produto p1 = new Produto(null, "Coca-cola 2l", 15.00, r1);
		Produto p2 = new Produto(null, "Suco de laranja", 8.00, r1);
		Produto p3 = new Produto(null, "X-Bacon", 20.00, r2);
		Produto p5 = new Produto(null, "X-Egg", 18.00, r1);
		Produto p6 = new Produto(null, "Suco de Morango", 20.00, r3);
		Produto p7 = new Produto(null, "Pastel de Frango", 10.00, r4);
		Produto p8 = new Produto(null, "Milk Shake Ovomaltine", 9.00, r5);
		Produto p9 = new Produto(null, "Bolo no pote", 6.00, r1);
		Produto p10 = new Produto(null, "Pastel de queijo", 20.00, r4);
		Produto p11 = new Produto(null, "Dipirona", 6.00, r1);

		// pizza
		PizzaSaborTamanho PortuguesaGrande = new PizzaSaborTamanho(null, "Portuguesa",
				"Molho, Mussarela, Tomate, presunto, ovo, cebola e orégano", "Grande", 35.00);
		PizzaSaborTamanho MussarelaGrande = new PizzaSaborTamanho(null, "Mussarela",
				"Molho, Mussarela, Tomate e orégano", "Grande", 30.00);
		PizzaSaborTamanho MussarelaMedia = new PizzaSaborTamanho(null, "Mussarela",
				"Molho, Mussarela, Tomate e orégano", "Media", 26.00);
		PizzaSaborTamanho MussarelaBroto = new PizzaSaborTamanho(null, "Mussarela",
				"Molho, Mussarela, Tomate e orégano", "Broto", 22.00);

		pizzaSaborTamanhoRepository
				.saveAll(Arrays.asList(MussarelaGrande, MussarelaMedia, MussarelaBroto, PortuguesaGrande));

		PizzaMassa bordaSimples = new PizzaMassa(null, "Borda simples", 2.00);
		pizzaMassaRepository.saveAll(Arrays.asList(bordaSimples));

		Adicional ad1 = new Adicional(null, "Calabresa", 6.00, TipoAdicional.PIZZA.getCod());
		adicionalRepository.saveAll(Arrays.asList(ad1));

		Pizza p4 = new Pizza(Arrays.asList(MussarelaGrande), bordaSimples, Arrays.asList(ad1), "tirar tomate", 2);
		Pizza p12 = new Pizza(Arrays.asList(MussarelaGrande, PortuguesaGrande), bordaSimples, Arrays.asList(ad1),
				"tirar cebola", 2);

		SorveteCobertura coberturaCaramelo = new SorveteCobertura(null, "Caramelo", 0.0);
		sorveteCoberturaRepository.save(coberturaCaramelo);

		SorveteSabor chocolate = new SorveteSabor(null, "Chocolate", 0.0);
		sorveteSaborRepository.save(chocolate);

		SorveteTamanho tamanhoSorvete = new SorveteTamanho(null, "Copinho de 1 bola", 4.00,
				"Copinho de 1 bola com 1 sabor a sua escolha.", 1);
		sorveteTamanhoRepository.save(tamanhoSorvete);

		Adicional adCastanha = new Adicional(null, "Castanha", 1.00, TipoAdicional.SORVETE.getCod());
		adicionalRepository.saveAll(Arrays.asList(adCastanha));

		Sorvete sorvete = new Sorvete(tamanhoSorvete, Arrays.asList(chocolate), Arrays.asList(adCastanha),
				Arrays.asList(coberturaCaramelo), TipoProduto.SORVETE.getCod());
		sorveteRepository.save(sorvete);

		Produto p13 = new Produto(null, "Produto 13", 10.00, r1);
		Produto p14 = new Produto(null, "Produto 14", 10.00, r1);
		Produto p15 = new Produto(null, "Produto 15", 10.00, r1);
		Produto p16 = new Produto(null, "Produto 16", 10.00, r1);
		Produto p17 = new Produto(null, "Produto 17", 10.00, r1);
		Produto p18 = new Produto(null, "Produto 18", 10.00, r1);
		Produto p19 = new Produto(null, "Produto 19", 10.00, r1);
		Produto p20 = new Produto(null, "Produto 20", 10.00, r1);
		Produto p21 = new Produto(null, "Produto 21", 10.00, r1);
		Produto p22 = new Produto(null, "Produto 22", 10.00, r1);
		Produto p23 = new Produto(null, "Produto 23", 10.00, r1);
		Produto p24 = new Produto(null, "Produto 24", 10.00, r1);
		Produto p25 = new Produto(null, "Produto 25", 10.00, r1);
		Produto p26 = new Produto(null, "Produto 26", 10.00, r1);
		Produto p27 = new Produto(null, "Produto 27", 10.00, r1);
		Produto p28 = new Produto(null, "Produto 28", 10.00, r1);
		Produto p29 = new Produto(null, "Produto 29", 10.00, r1);
		Produto p30 = new Produto(null, "Produto 30", 10.00, r1);
		Produto p31 = new Produto(null, "Produto 31", 10.00, r1);
		Produto p32 = new Produto(null, "Produto 32", 10.00, r1);
		Produto p33 = new Produto(null, "Produto 33", 10.00, r1);
		Produto p34 = new Produto(null, "Produto 34", 10.00, r1);
		Produto p35 = new Produto(null, "Produto 35", 10.00, r1);
		Produto p36 = new Produto(null, "Produto 36", 10.00, r1);
		Produto p37 = new Produto(null, "Produto 37", 10.00, r1);
		Produto p38 = new Produto(null, "Produto 38", 10.00, r1);
		Produto p39 = new Produto(null, "Produto 39", 10.00, r1);
		Produto p40 = new Produto(null, "Produto 40", 10.00, r1);
		Produto p41 = new Produto(null, "Produto 41", 10.00, r1);
		Produto p42 = new Produto(null, "Produto 42", 10.00, r1);
		Produto p43 = new Produto(null, "Produto 43", 10.00, r1);
		Produto p44 = new Produto(null, "Produto 44", 10.00, r1);
		Produto p45 = new Produto(null, "Produto 45", 10.00, r1);
		Produto p46 = new Produto(null, "Produto 46", 10.00, r1);
		Produto p47 = new Produto(null, "Produto 47", 10.00, r1);
		Produto p48 = new Produto(null, "Produto 48", 10.00, r1);
		Produto p49 = new Produto(null, "Produto 49", 10.00, r1);
		Produto p50 = new Produto(null, "Produto 50", 10.00, r1);
		
		p50.setCodBarras("55247022");

		cat1.getProdutos()
				.addAll(Arrays.asList(p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28,
						p29, p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48,
						p49, p50));

		p13.setCategoria(cat1);
		p3.setCategoria(cat1);
		p5.setCategoria(cat1);

		p1.setCategoria(cat2);
		p2.setCategoria(cat2);
		p4.setCategoria(cat3);
		p6.setCategoria(cat2);
		p7.setCategoria(cat5);
		p8.setCategoria(cat2);
		p9.setCategoria(cat4);
		p10.setCategoria(cat5);
		p12.setCategoria(cat3);

		for (Produto p : Arrays.asList(p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, 
		                               p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, 
		                               p47, p48, p49, p50)) {
		    p.setCategoria(cat1);
		}

		cat1.getProdutos().addAll(Arrays.asList(p3, p5, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, 
		                                        p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, 
		                                        p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		cat2.getProdutos().addAll(Arrays.asList(p1, p2, p6, p8));
		cat3.getProdutos().addAll(Arrays.asList(p4, p12));
		cat4.getProdutos().addAll(Arrays.asList(p9));
		cat5.getProdutos().addAll(Arrays.asList(p7, p10));		

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));

		produtoRepository.saveAll(
				Arrays.asList(p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30,
						p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("10/09/2022 10:32"), cli1, e1, r1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2022 10:50"), cli1, e2, r2);

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);

		Pagamento pgto2 = new PagamentoComCartao(null, EstadoPagamento.PENDENTE, ped2, 6);
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
