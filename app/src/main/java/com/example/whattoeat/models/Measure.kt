package com.example.whattoeat.models
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal
import java.math.RoundingMode

@Serializable
data class Measure (
//    val amount: Double = 0.00,
    @Serializable(with = AmountSerializable::class) val amount:Double = 0.00,
    val unitShort: String = "",
    val unitLong: String =""
)

// To force the size of double
// https://stackoverflow.com/questions/77575434/kotlin-serialize-float-element-with-3-decimal-places

class AmountSerializable: KSerializer<Double> {
    override val descriptor = PrimitiveSerialDescriptor("amount", PrimitiveKind.DOUBLE)

    override fun deserialize(decoder: Decoder): Double {
        return BigDecimal(decoder.decodeDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    }

    override fun serialize(encoder: Encoder, value: Double) {
        return encoder.encodeDouble(value)
    }
}