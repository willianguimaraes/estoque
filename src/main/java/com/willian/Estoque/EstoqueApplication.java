package com.willian.Estoque;

import com.willian.Estoque.domain.*;
import com.willian.Estoque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class EstoqueApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(EstoqueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = Usuario.builder()
				.nome("Go Rastreamentos de Veiculos LTDA")
				.email("daniel@gorastreamentos.com.br")
				.cpfCnpj("31321442000163")
				.senha(pe.encode("102030"))
				.build();

		usuarioRepository.saveAll(Arrays.asList(usuario));

		Marca marca1 = Marca.builder()
				.descricao("Quanta Tecnologia")
				.build();

		Marca marca2 = Marca.builder()
				.descricao("Suntech")
				.build();

		Categoria c1 = Categoria.builder()
				.descricao("Rastreadores")
				.build();

		Categoria c2 = Categoria.builder()
				.descricao("Materiais Instalação")
				.build();

		Produto p1 = Produto.builder()
				.categorias(Arrays.asList(c1))
				.descricao("Radar Duo V1")
				.marca(marca1)
				.numSerie("15767")
				.build();

		Produto p2 = Produto.builder()
				.categorias(Arrays.asList(c1))
				.descricao("ST 310 U")
				.marca(marca2)
				.numSerie("007382382")
				.build();

		c1.setProdutos(Arrays.asList(p1, p2));
		marca1.setProdutos(Arrays.asList(p1));
		marca2.setProdutos(Arrays.asList(p2));

		marcaRepository.saveAll(Arrays.asList(marca1, marca2));
		categoriaRepository.saveAll(Arrays.asList(c1,c2));
		produtoRepository.saveAll(Arrays.asList(p1,p2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Cliente cli1 = Cliente.builder()
				.nome("Willian Fernandes Guimaraes")
				.cpfCnpj("01013704908")
				.build();

		Cliente cli2 = Cliente.builder()
				.nome("Vinicius Antonio Movio de Faria")
				.cpfCnpj("01013704908")
				.build();

		Pedido ped1 = Pedido.builder()
				.cliente(cli1)
				.dataInicio(sdf.parse("30/09/2017 10:32"))
				.usuario(usuario)
				.build();

		Pedido ped2 = Pedido.builder()
				.cliente(cli2)
				.dataInicio(sdf.parse("10/11/2018 11:03"))
				.usuario(usuario)
				.build();

		cli1.setPedidos(Arrays.asList(ped1));
		cli2.setPedidos(Arrays.asList(ped2));

		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));


		ItemPedido item1 = new ItemPedido(ped1, p1, 1);
		ItemPedido item2 = new ItemPedido(ped2, p2, 1);

		ped1.setItens(Arrays.asList(item1));
		ped2.setItens(Arrays.asList(item2));

		p1.setItens(Arrays.asList(item1));
		p2.setItens(Arrays.asList(item2));

		itemPedidoRepository.saveAll(Arrays.asList(item1,item2));

	}
}
