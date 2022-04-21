CREATE FUNCTION calcular_valor_total() RETURNS trigger AS $calcular$
    BEGIN
        UPDATE ped set 
			valor_total = (select SUM(pp.valor_unidade * pp.qtd) from pedido_produto pp where pp.id_pedido = NEW.id_pedido)
		from Pedido ped
		where ped.id_pedido = NEW.id_pedido;
		RETURN NEW;
    END;
$calcular$ LANGUAGE plpgsql;

CREATE TRIGGER calcular_valor_total_pedido
    AFTER INSERT OR UPDATE ON pedido_produto
	FOR EACH ROW
	EXECUTE PROCEDURE calcular_valor_total();