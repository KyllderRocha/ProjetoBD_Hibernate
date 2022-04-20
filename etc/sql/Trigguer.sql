CREATE TRIGGER calcular_valor_total_pedido
    AFTER INSERT OR UPDATE ON pedido_produto
	UPDATE ped set 
		valor_total = (select SUM(pp.valor_unidade * pp.qtd) from pedido_produto pp where pp.id_pedido = NEW.id_pedido)
	from Pedido ped
	where ped.id_pedido = NEW.id_pedido
	