package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Pedidos;
import com.projetojpa.service.PedidosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Pedido", description = "API REST DE GERENCIAMENTO DO Gerenciamento")
@RestController
@RequestMapping("/Pedido")
public class PedidosController {
	
	private final PedidosService pedidosService;

	@Autowired
	public PedidosController(PedidosService pedidosService) {
		this.pedidosService = pedidosService;
	}

	@GetMapping ("/{id}")
	@Operation(summary = "Busca um pedido pelo Id")
	public ResponseEntity<Pedidos> buscaPedidosIdControlId (@ PathVariable Long id) {
		Pedidos pedidos = pedidosService.buscaPedidosId(id);
		if (pedidos != null) {
			return ResponseEntity.ok(pedidos);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Busca todos os pedidos")
	public ResponseEntity<List<Pedidos>> buscaTodosPedidosControl(){
		List<Pedidos> pedidos = pedidosService.buscaTodosPedidos();
		return ResponseEntity.ok(pedidos);
	}
	@PostMapping("/")
	public ResponseEntity<Pedidos> salvaPedidosControl(@RequestBody @Valid Pedidos pedidos){
		Pedidos salvaPedidos = pedidosService.salvaPedidos(pedidos);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaPedidos);
	}
	@PutMapping("/{id}")
	@Operation(summary = "Altera um pedido")
	public ResponseEntity<Pedidos> alterarPedidosControl(@PathVariable Long id, @RequestBody @Valid Pedidos pedidos){
		Pedidos alterarPedidos = pedidosService.alterarPedidos(id, pedidos);
		if(alterarPedidos != null) {
			return ResponseEntity.ok(alterarPedidos);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um pedido")
	public ResponseEntity<String> apagaPedidosControl(@PathVariable Long id){
		boolean pedidos = pedidosService.apagarPedidos(id);
		if (pedidos) {
			return ResponseEntity.ok().body("O produto foi excluído com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
