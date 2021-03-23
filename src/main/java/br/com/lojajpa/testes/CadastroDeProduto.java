package br.com.lojajpa.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.lojajpa.dao.CategoriaDao;
import br.com.lojajpa.dao.ProdutoDao;
import br.com.lojajpa.model.Categoria;
import br.com.lojajpa.model.Produto;
import br.com.lojajpa.util.JPAUtil;

public class CadastroDeProduto {
	public static void main(String[] args) {

		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto produto = produtoDao.buscarProdutoPorId(1l);
		
		System.out.println(produto.getPreco());
		
		List<Produto> produtos = produtoDao.buscarPorNomeDaCategoria("INFORMATICA");
		produtos.forEach(p -> System.out.println(p.getNome()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoPorNome("Computador");
		
		System.out.println(precoDoProduto);
		
	}

	private static void cadastrarProduto() {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Categoria categoria = new Categoria("INFORMATICA");
		
		Produto produto = new Produto("Computador", "gamer", new BigDecimal("7000"), categoria);
		
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(produto);
		
		em.getTransaction().commit();
		em.close();
	}
}
