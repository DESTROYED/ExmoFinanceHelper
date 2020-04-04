package com.destr.financehelper.data.datasource.cloud

import com.destr.financehelper.data.datasource.cloud.response.PairDetail
import com.google.gson.*
import java.lang.reflect.Type

class JsonMapDeserializer : JsonDeserializer<Map<String, PairDetail>> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Map<String, PairDetail> {
        val result: MutableMap<String, PairDetail> = HashMap()
        val parentAsObject: JsonObject? = json?.asJsonObject
        parentAsObject?.entrySet()?.let {
            for ((key, value) in it) {
                result[key] = Gson().fromJson(value, PairDetail::class.java)
            }
        }
        return result
    }
}