package com.joselbe.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
internal class ShopRepsonseEntity(val result : List<ShopEntity>)