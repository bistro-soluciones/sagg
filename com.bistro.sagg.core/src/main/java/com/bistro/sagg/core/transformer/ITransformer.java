package com.bistro.sagg.core.transformer;

public interface ITransformer<T, U> {

	U transform(T object);
	
}
