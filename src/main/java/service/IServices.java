package service;

import java.util.List;

public interface IServices <T> {
	public T adicionar(T item);
	public T obterPorId(Long id);
	public List<T> obterTudo();
	public void deletarPorId(T item);
	public T atualizar(T item);
}