package service

fun isValidPesel(pesel: String): Boolean {
    if(pesel.length != 11)
        return false
    val weights = intArrayOf(1, 3, 7, 9, 1, 3, 7, 9, 1, 3)
    var csum = pesel.substring(pesel.length-1).toInt()
    var sum = 0
    pesel
            .filterIndexed { index, c -> index < 10 }
            .mapIndexed { index, c -> sum += c.toString().toInt() * weights[index] }
    return (10 - (sum % 10))%10 == csum
}