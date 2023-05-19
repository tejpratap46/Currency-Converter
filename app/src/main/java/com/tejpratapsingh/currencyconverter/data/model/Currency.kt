package com.tejpratapsingh.currencyconverter.data.model

sealed class Currency(val code: String, val name: String) {
    companion object {
        fun asList(): List<Currency> {
            return listOf(
                AED,
                AFN,
                ALL,
                AMD,
                ANG,
                AOA,
                ARS,
                AUD,
                AWG,
                AZN,
                BAM,
                BBD,
                BDT,
                BGN,
                BHD,
                BIF,
                BMD,
                BND,
                BOB,
                BRL,
                BSD,
                BTC,
                BTN,
                BWP,
                BYN,
                BYR,
                BZD,
                CAD,
                CDF,
                CHF,
                CLF,
                CLP,
                CNY,
                COP,
                CRC,
                CUC,
                CUP,
                CVE,
                CZK,
                DJF,
                DKK,
                DOP,
                DZD,
                EGP,
                ERN,
                ETB,
                EUR,
                FJD,
                FKP,
                GBP,
                GEL,
                GGP,
                GHS,
                GIP,
                GMD,
                GNF,
                GTQ,
                GYD,
                HKD,
                HNL,
                HRK,
                HTG,
                HUF,
                IDR,
                ILS,
                IMP,
                INR,
                IQD,
                IRR,
                ISK,
                JEP,
                JMD,
                JOD,
                JPY,
                KES,
                KGS,
                KHR,
                KMF,
                KPW,
                KRW,
                KWD,
                KYD,
                KZT,
                LAK,
                LBP,
                LKR,
                LRD,
                LSL,
                LTL,
                LVL,
                LYD,
                MAD,
                MDL,
                MGA,
                MKD,
                MMK,
                MNT,
                MOP,
                MRO,
                MUR,
                MVR,
                MWK,
                MXN,
                MYR,
                MZN,
                NAD,
                NGN,
                NIO,
                NOK,
                NPR,
                NZD,
                OMR,
                PAB,
                PEN,
                PGK,
                PHP,
                PKR,
                PLN,
                PYG,
                QAR,
                RON,
                RSD,
                RUB,
                RWF,
                SAR,
                SBD,
                SCR,
                SDG,
                SEK,
                SGD,
                SHP,
                SLE,
                SLL,
                SOS,
                SRD,
                STD,
                SVC,
                SYP,
                SZL,
                THB,
                TJS,
                TMT,
                TND,
                TOP,
                TRY,
                TTD,
                TWD,
                TZS,
                UAH,
                UGX,
                USD,
                UYU,
                UZS,
                VEF,
                VES,
                VND,
                VUV,
                WST,
                XAF,
                XAG,
                XAU,
                XCD,
                XDR,
                XOF,
                XPF,
                YER,
                ZAR,
                ZMK,
                ZMW,
                ZWL,
            )
        }
    }

    object AED : Currency("AED", "United Arab Emirates Dirham")
    object AFN : Currency("AFN", "Afghan Afghani")
    object ALL : Currency("ALL", "Albanian Lek")
    object AMD : Currency("AMD", "Armenian Dram")
    object ANG : Currency("ANG", "Netherlands Antillean Guilder")
    object AOA : Currency("AOA", "Angolan Kwanza")
    object ARS : Currency("ARS", "Argentine Peso")
    object AUD : Currency("AUD", "Australian Dollar")
    object AWG : Currency("AWG", "Aruban Florin")
    object AZN : Currency("AZN", "Azerbaijani Manat")
    object BAM : Currency("BAM", "Bosnia-Herzegovina Convertible Mark")
    object BBD : Currency("BBD", "Barbadian Dollar")
    object BDT : Currency("BDT", "Bangladeshi Taka")
    object BGN : Currency("BGN", "Bulgarian Lev")
    object BHD : Currency("BHD", "Bahraini Dinar")
    object BIF : Currency("BIF", "Burundian Franc")
    object BMD : Currency("BMD", "Bermudan Dollar")
    object BND : Currency("BND", "Brunei Dollar")
    object BOB : Currency("BOB", "Bolivian Boliviano")
    object BRL : Currency("BRL", "Brazilian Real")
    object BSD : Currency("BSD", "Bahamian Dollar")
    object BTC : Currency("BTC", "Bitcoin")
    object BTN : Currency("BTN", "Bhutanese Ngultrum")
    object BWP : Currency("BWP", "Botswana Pula")
    object BYN : Currency("BYN", "New Belarusian Ruble")
    object BYR : Currency("BYR", "Belarusian Ruble")
    object BZD : Currency("BZD", "Belize Dollar")
    object CAD : Currency("CAD", "Canadian Dollar")
    object CDF : Currency("CDF", "Congolese Franc")
    object CHF : Currency("CHF", "Swiss Franc")
    object CLF : Currency("CLF", "Chilean Unit of Account (UF)")
    object CLP : Currency("CLP", "Chilean Peso")
    object CNY : Currency("CNY", "Chinese Yuan")
    object COP : Currency("COP", "Colombian Peso")
    object CRC : Currency("CRC", "Costa Rican Colón")
    object CUC : Currency("CUC", "Cuban Convertible Peso")
    object CUP : Currency("CUP", "Cuban Peso")
    object CVE : Currency("CVE", "Cape Verdean Escudo")
    object CZK : Currency("CZK", "Czech Republic Koruna")
    object DJF : Currency("DJF", "Djiboutian Franc")
    object DKK : Currency("DKK", "Danish Krone")
    object DOP : Currency("DOP", "Dominican Peso")
    object DZD : Currency("DZD", "Algerian Dinar")
    object EGP : Currency("EGP", "Egyptian Pound")
    object ERN : Currency("ERN", "Eritrean Nakfa")
    object ETB : Currency("ETB", "Ethiopian Birr")
    object EUR : Currency("EUR", "Euro")
    object FJD : Currency("FJD", "Fijian Dollar")
    object FKP : Currency("FKP", "Falkland Islands Pound")
    object GBP : Currency("GBP", "British Pound Sterling")
    object GEL : Currency("GEL", "Georgian Lari")
    object GGP : Currency("GGP", "Guernsey Pound")
    object GHS : Currency("GHS", "Ghanaian Cedi")
    object GIP : Currency("GIP", "Gibraltar Pound")
    object GMD : Currency("GMD", "Gambian Dalasi")
    object GNF : Currency("GNF", "Guinean Franc")
    object GTQ : Currency("GTQ", "Guatemalan Quetzal")
    object GYD : Currency("GYD", "Guyanaese Dollar")
    object HKD : Currency("HKD", "Hong Kong Dollar")
    object HNL : Currency("HNL", "Honduran Lempira")
    object HRK : Currency("HRK", "Croatian Kuna")
    object HTG : Currency("HTG", "Haitian Gourde")
    object HUF : Currency("HUF", "Hungarian Forint")
    object IDR : Currency("IDR", "Indonesian Rupiah")
    object ILS : Currency("ILS", "Israeli New Sheqel")
    object IMP : Currency("IMP", "Manx pound")
    object INR : Currency("INR", "Indian Rupee")
    object IQD : Currency("IQD", "Iraqi Dinar")
    object IRR : Currency("IRR", "Iranian Rial")
    object ISK : Currency("ISK", "Icelandic Króna")
    object JEP : Currency("JEP", "Jersey Pound")
    object JMD : Currency("JMD", "Jamaican Dollar")
    object JOD : Currency("JOD", "Jordanian Dinar")
    object JPY : Currency("JPY", "Japanese Yen")
    object KES : Currency("KES", "Kenyan Shilling")
    object KGS : Currency("KGS", "Kyrgystani Som")
    object KHR : Currency("KHR", "Cambodian Riel")
    object KMF : Currency("KMF", "Comorian Franc")
    object KPW : Currency("KPW", "North Korean Won")
    object KRW : Currency("KRW", "South Korean Won")
    object KWD : Currency("KWD", "Kuwaiti Dinar")
    object KYD : Currency("KYD", "Cayman Islands Dollar")
    object KZT : Currency("KZT", "Kazakhstani Tenge")
    object LAK : Currency("LAK", "Laotian Kip")
    object LBP : Currency("LBP", "Lebanese Pound")
    object LKR : Currency("LKR", "Sri Lankan Rupee")
    object LRD : Currency("LRD", "Liberian Dollar")
    object LSL : Currency("LSL", "Lesotho Loti")
    object LTL : Currency("LTL", "Lithuanian Litas")
    object LVL : Currency("LVL", "Latvian Lats")
    object LYD : Currency("LYD", "Libyan Dinar")
    object MAD : Currency("MAD", "Moroccan Dirham")
    object MDL : Currency("MDL", "Moldovan Leu")
    object MGA : Currency("MGA", "Malagasy Ariary")
    object MKD : Currency("MKD", "Macedonian Denar")
    object MMK : Currency("MMK", "Myanma Kyat")
    object MNT : Currency("MNT", "Mongolian Tugrik")
    object MOP : Currency("MOP", "Macanese Pataca")
    object MRO : Currency("MRO", "Mauritanian Ouguiya")
    object MUR : Currency("MUR", "Mauritian Rupee")
    object MVR : Currency("MVR", "Maldivian Rufiyaa")
    object MWK : Currency("MWK", "Malawian Kwacha")
    object MXN : Currency("MXN", "Mexican Peso")
    object MYR : Currency("MYR", "Malaysian Ringgit")
    object MZN : Currency("MZN", "Mozambican Metical")
    object NAD : Currency("NAD", "Namibian Dollar")
    object NGN : Currency("NGN", "Nigerian Naira")
    object NIO : Currency("NIO", "Nicaraguan Córdoba")
    object NOK : Currency("NOK", "Norwegian Krone")
    object NPR : Currency("NPR", "Nepalese Rupee")
    object NZD : Currency("NZD", "New Zealand Dollar")
    object OMR : Currency("OMR", "Omani Rial")
    object PAB : Currency("PAB", "Panamanian Balboa")
    object PEN : Currency("PEN", "Peruvian Nuevo Sol")
    object PGK : Currency("PGK", "Papua New Guinean Kina")
    object PHP : Currency("PHP", "Philippine Peso")
    object PKR : Currency("PKR", "Pakistani Rupee")
    object PLN : Currency("PLN", "Polish Zloty")
    object PYG : Currency("PYG", "Paraguayan Guarani")
    object QAR : Currency("QAR", "Qatari Rial")
    object RON : Currency("RON", "Romanian Leu")
    object RSD : Currency("RSD", "Serbian Dinar")
    object RUB : Currency("RUB", "Russian Ruble")
    object RWF : Currency("RWF", "Rwandan Franc")
    object SAR : Currency("SAR", "Saudi Riyal")
    object SBD : Currency("SBD", "Solomon Islands Dollar")
    object SCR : Currency("SCR", "Seychellois Rupee")
    object SDG : Currency("SDG", "Sudanese Pound")
    object SEK : Currency("SEK", "Swedish Krona")
    object SGD : Currency("SGD", "Singapore Dollar")
    object SHP : Currency("SHP", "Saint Helena Pound")
    object SLE : Currency("SLE", "Sierra Leonean Leone")
    object SLL : Currency("SLL", "Sierra Leonean Leone")
    object SOS : Currency("SOS", "Somali Shilling")
    object SRD : Currency("SRD", "Surinamese Dollar")
    object STD : Currency("STD", "São Tomé and Príncipe Dobra")
    object SVC : Currency("SVC", "Salvadoran Colón")
    object SYP : Currency("SYP", "Syrian Pound")
    object SZL : Currency("SZL", "Swazi Lilangeni")
    object THB : Currency("THB", "Thai Baht")
    object TJS : Currency("TJS", "Tajikistani Somoni")
    object TMT : Currency("TMT", "Turkmenistani Manat")
    object TND : Currency("TND", "Tunisian Dinar")
    object TOP : Currency("TOP", "Tongan Paʻanga")
    object TRY : Currency("TRY", "Turkish Lira")
    object TTD : Currency("TTD", "Trinidad and Tobago Dollar")
    object TWD : Currency("TWD", "New Taiwan Dollar")
    object TZS : Currency("TZS", "Tanzanian Shilling")
    object UAH : Currency("UAH", "Ukrainian Hryvnia")
    object UGX : Currency("UGX", "Ugandan Shilling")
    object USD : Currency("USD", "United States Dollar")
    object UYU : Currency("UYU", "Uruguayan Peso")
    object UZS : Currency("UZS", "Uzbekistan Som")
    object VEF : Currency("VEF", "Venezuelan Bolívar Fuerte")
    object VES : Currency("VES", "Sovereign Bolivar")
    object VND : Currency("VND", "Vietnamese Dong")
    object VUV : Currency("VUV", "Vanuatu Vatu")
    object WST : Currency("WST", "Samoan Tala")
    object XAF : Currency("XAF", "CFA Franc BEAC")
    object XAG : Currency("XAG", "Silver troy ounce")
    object XAU : Currency("XAU", "Gold troy ounce")
    object XCD : Currency("XCD", "East Caribbean Dollar")
    object XDR : Currency("XDR", "Special Drawing Rights")
    object XOF : Currency("XOF", "CFA Franc BCEAO")
    object XPF : Currency("XPF", "CFP Franc")
    object YER : Currency("YER", "Yemeni Rial")
    object ZAR : Currency("ZAR", "South African Rand")
    object ZMK : Currency("ZMK", "Zambian Kwacha pre-2013")
    object ZMW : Currency("ZMW", "Zambian Kwacha")
    object ZWL : Currency("ZWL", "Zimbabwean Dollar")
}
