package com.willian.Estoque;

import com.willian.Estoque.domain.Categoria;
import com.willian.Estoque.domain.Marca;
import com.willian.Estoque.domain.Produto;
import com.willian.Estoque.domain.Usuario;
import com.willian.Estoque.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class EstoqueApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(EstoqueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = Usuario.builder()
				.nome("Go Rastreamentos de Veiculos LTDA")
				.email("daniel@gorastreamentos.com.br")
				.cpfCnpj("31321442000163")
				.senha("102030")
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



	}
}
