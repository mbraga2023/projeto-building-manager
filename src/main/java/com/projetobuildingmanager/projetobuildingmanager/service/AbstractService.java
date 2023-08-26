package com.projetobuildingmanager.projetobuildingmanager.service;

import com.projetobuildingmanager.projetobuildingmanager.models.AbstractEntity;

public interface AbstractService<T extends AbstractEntity> {

	void salvar(T entidade);

	void salvar(T entidade, String imagem);
}