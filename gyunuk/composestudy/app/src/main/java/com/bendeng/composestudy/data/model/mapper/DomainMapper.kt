package com.bendeng.composestudy.data.model.mapper

import com.bendeng.composestudy.data.model.base.BaseDataModel
import com.bendeng.composestudy.domain.model.base.BaseDomainModel

interface DomainMapper<in R : BaseDataModel, out D : BaseDomainModel> {
    fun R.toDomainModel(): D
}