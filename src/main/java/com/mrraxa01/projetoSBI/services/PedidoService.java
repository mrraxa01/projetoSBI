package com.mrraxa01.projetoSBI.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrraxa01.projetoSBI.domain.ItemPedido;
import com.mrraxa01.projetoSBI.domain.PagamentoComBoleto;
import com.mrraxa01.projetoSBI.domain.Pedido;
import com.mrraxa01.projetoSBI.domain.enums.EstadoPagamento;
import com.mrraxa01.projetoSBI.repositories.ItemPedidoRepository;
import com.mrraxa01.projetoSBI.repositories.PagamentoRepository;
import com.mrraxa01.projetoSBI.repositories.PedidoRepository;
import com.mrraxa01.projetoSBI.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	public PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}

		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}

		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}
