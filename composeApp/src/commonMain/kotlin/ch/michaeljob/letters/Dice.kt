package ch.michaeljob.letters

import letters.composeapp.generated.resources.Res
import letters.composeapp.generated.resources.dice124
import letters.composeapp.generated.resources.dice132
import letters.composeapp.generated.resources.dice145
import letters.composeapp.generated.resources.dice153
import letters.composeapp.generated.resources.dice213
import letters.composeapp.generated.resources.dice236
import letters.composeapp.generated.resources.dice241
import letters.composeapp.generated.resources.dice264
import letters.composeapp.generated.resources.dice315
import letters.composeapp.generated.resources.dice321
import letters.composeapp.generated.resources.dice356
import letters.composeapp.generated.resources.dice362
import letters.composeapp.generated.resources.dice412
import letters.composeapp.generated.resources.dice426
import letters.composeapp.generated.resources.dice451
import letters.composeapp.generated.resources.dice465
import letters.composeapp.generated.resources.dice514
import letters.composeapp.generated.resources.dice531
import letters.composeapp.generated.resources.dice546
import letters.composeapp.generated.resources.dice563
import letters.composeapp.generated.resources.dice623
import letters.composeapp.generated.resources.dice635
import letters.composeapp.generated.resources.dice642
import letters.composeapp.generated.resources.dice654
import letters.composeapp.generated.resources.dice124_inv
import letters.composeapp.generated.resources.dice132_inv
import letters.composeapp.generated.resources.dice145_inv
import letters.composeapp.generated.resources.dice153_inv
import letters.composeapp.generated.resources.dice213_inv
import letters.composeapp.generated.resources.dice236_inv
import letters.composeapp.generated.resources.dice241_inv
import letters.composeapp.generated.resources.dice264_inv
import letters.composeapp.generated.resources.dice315_inv
import letters.composeapp.generated.resources.dice321_inv
import letters.composeapp.generated.resources.dice356_inv
import letters.composeapp.generated.resources.dice362_inv
import letters.composeapp.generated.resources.dice412_inv
import letters.composeapp.generated.resources.dice426_inv
import letters.composeapp.generated.resources.dice451_inv
import letters.composeapp.generated.resources.dice465_inv
import letters.composeapp.generated.resources.dice514_inv
import letters.composeapp.generated.resources.dice531_inv
import letters.composeapp.generated.resources.dice546_inv
import letters.composeapp.generated.resources.dice563_inv
import letters.composeapp.generated.resources.dice623_inv
import letters.composeapp.generated.resources.dice635_inv
import letters.composeapp.generated.resources.dice642_inv
import letters.composeapp.generated.resources.dice654_inv
import org.jetbrains.compose.resources.DrawableResource

enum class Dice(val value: Int, val drawable: DrawableResource, val drawable_inv: DrawableResource) {
    ONE24(1, Res.drawable.dice124, Res.drawable.dice124_inv),
    ONE32(1, Res.drawable.dice132, Res.drawable.dice132_inv),
    ONE45(1, Res.drawable.dice145, Res.drawable.dice145_inv),
    ONE53(1, Res.drawable.dice153, Res.drawable.dice153_inv),
    TWO13(2, Res.drawable.dice213, Res.drawable.dice213_inv),
    TWO36(2, Res.drawable.dice236, Res.drawable.dice236_inv),
    TWO41(2, Res.drawable.dice241, Res.drawable.dice241_inv),
    TWO64(2, Res.drawable.dice264, Res.drawable.dice264_inv),
    THREE15(3, Res.drawable.dice315, Res.drawable.dice315_inv),
    THREE21(3, Res.drawable.dice321, Res.drawable.dice321_inv),
    THREE56(3, Res.drawable.dice356, Res.drawable.dice356_inv),
    THREE62(3, Res.drawable.dice362, Res.drawable.dice362_inv),
    FOUR12(4, Res.drawable.dice412, Res.drawable.dice412_inv),
    FOUR51(4, Res.drawable.dice451, Res.drawable.dice451_inv),
    FOUR26(4, Res.drawable.dice426, Res.drawable.dice426_inv),
    FOUR65(4, Res.drawable.dice465, Res.drawable.dice465_inv),
    FIVE46(5, Res.drawable.dice546, Res.drawable.dice546_inv),
    FIVE63(5, Res.drawable.dice563, Res.drawable.dice563_inv),
    FIVE31(5, Res.drawable.dice531, Res.drawable.dice531_inv),
    FIVE15(5, Res.drawable.dice514, Res.drawable.dice514_inv),
    SIX54(6, Res.drawable.dice654, Res.drawable.dice654_inv),
    SIX42(6, Res.drawable.dice642, Res.drawable.dice642_inv),
    SIX35(6, Res.drawable.dice635, Res.drawable.dice635_inv),
    SIX23(6, Res.drawable.dice623, Res.drawable.dice623_inv),
}