package org.kiteseven.kitecommon.converter;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class JapaneseToRomaji {
    private static final Tokenizer tokenizer = new Tokenizer.Builder().build();
    private static final Pattern SMALL_TSU = Pattern.compile("っ|ッ");
    private static final Pattern LONG_VOWEL = Pattern.compile("ー");

    public static String convert(String japanese) {
        List<Token> tokens = tokenizer.tokenize(japanese);
        StringBuilder romaji = new StringBuilder();

        for (Token token : tokens) {
            String reading = getEffectiveReading(token);
            romaji.append(processSpecialCases(convertKanaToRomaji(reading)));
        }

        return romaji.toString().trim();
    }

    private static String getEffectiveReading(Token token) {
        // 优先使用词典中的读音，否则使用表面形态
        return token.getReading() == null ?
                token.getSurface() :
                token.getReading();
    }

    private static String processSpecialCases(String input) {
        String processed = SMALL_TSU.matcher(input).replaceAll("");
        processed = LONG_VOWEL.matcher(processed).replaceAll("");
        return processed.toLowerCase() + " ";
    }

    private static String convertKanaToRomaji(String kana) {
        StringBuilder result = new StringBuilder();
        int position = 0;
        int length = kana.length();

        while (position < length) {
            RomajiConversion conversion = findBestMatch(kana, position);
            result.append(conversion.romaji);
            position += conversion.consumedChars;
        }

        return result.toString();
    }

    private static RomajiConversion findBestMatch(String kana, int position) {
        // 优先检查3字符组合（特殊拗音）
        if (position + 3 <= kana.length()) {
            String triplet = kana.substring(position, position + 3);
            String romaji = KanaRomajiMap.getRomaji(triplet);
            if (romaji != null) {
                return new RomajiConversion(romaji, 3);
            }
        }

        // 检查2字符组合（标准拗音）
        if (position + 2 <= kana.length()) {
            String pair = kana.substring(position, position + 2);
            String romaji = KanaRomajiMap.getRomaji(pair);
            if (romaji != null) {
                return new RomajiConversion(romaji, 2);
            }
        }

        // 处理单个字符
        String single = kana.substring(position, position + 1);
        String singleRomaji = KanaRomajiMap.getRomaji(single);
        return new RomajiConversion(
                singleRomaji != null ? singleRomaji : single,
                1
        );
    }

    private static class RomajiConversion {
        final String romaji;
        final int consumedChars;

        RomajiConversion(String romaji, int consumedChars) {
            this.romaji = romaji;
            this.consumedChars = consumedChars;
        }
    }

    private static class KanaRomajiMap {
        private static final Map<String, String> MAPPING = new HashMap<>();

        static {
            // 平假名基础映射
            putMapping("あ", "a");  putMapping("い", "i");  putMapping("う", "u");  putMapping("え", "e");  putMapping("お", "o");
            putMapping("か", "ka"); putMapping("き", "ki"); putMapping("く", "ku"); putMapping("け", "ke"); putMapping("こ", "ko");
            putMapping("さ", "sa"); putMapping("し", "shi");putMapping("す", "su"); putMapping("せ", "se"); putMapping("そ", "so");
            putMapping("た", "ta"); putMapping("ち", "chi");putMapping("つ", "tsu");putMapping("て", "te"); putMapping("と", "to");
            putMapping("な", "na"); putMapping("に", "ni"); putMapping("ぬ", "nu"); putMapping("ね", "ne"); putMapping("の", "no");
            putMapping("は", "ha"); putMapping("ひ", "hi"); putMapping("ふ", "fu"); putMapping("へ", "he"); putMapping("ほ", "ho");
            putMapping("ま", "ma"); putMapping("み", "mi"); putMapping("む", "mu"); putMapping("め", "me"); putMapping("も", "mo");
            putMapping("や", "ya"); putMapping("ゆ", "yu"); putMapping("よ", "yo");
            putMapping("ら", "ra"); putMapping("り", "ri"); putMapping("る", "ru"); putMapping("れ", "re"); putMapping("ろ", "ro");
            putMapping("わ", "wa"); putMapping("を", "wo"); putMapping("ん", "n");

// 浊音/半浊音
            putMapping("が", "ga"); putMapping("ぎ", "gi"); putMapping("ぐ", "gu"); putMapping("げ", "ge"); putMapping("ご", "go");
            putMapping("ざ", "za"); putMapping("じ", "ji"); putMapping("ず", "zu"); putMapping("ぜ", "ze"); putMapping("ぞ", "zo");
            putMapping("だ", "da"); putMapping("ぢ", "ji"); putMapping("づ", "zu"); putMapping("で", "de"); putMapping("ど", "do");
            putMapping("ば", "ba"); putMapping("び", "bi"); putMapping("ぶ", "bu"); putMapping("べ", "be"); putMapping("ぼ", "bo");
            putMapping("ぱ", "pa"); putMapping("ぴ", "pi"); putMapping("ぷ", "pu"); putMapping("ぺ", "pe"); putMapping("ぽ", "po");

// 片假名基础映射（通过代码自动生成，此处无需重复添加）

// 拗音组合（清音）
            putMapping("きゃ", "kya"); putMapping("きゅ", "kyu"); putMapping("きょ", "kyo");
            putMapping("しゃ", "sha"); putMapping("しゅ", "shu"); putMapping("しょ", "sho");
            putMapping("ちゃ", "cha"); putMapping("ちゅ", "chu"); putMapping("ちょ", "cho");
            putMapping("にゃ", "nya"); putMapping("にゅ", "nyu"); putMapping("にょ", "nyo");
            putMapping("ひゃ", "hya"); putMapping("ひゅ", "hyu"); putMapping("ひょ", "hyo");
            putMapping("みゃ", "mya"); putMapping("みゅ", "myu"); putMapping("みょ", "myo");
            putMapping("りゃ", "rya"); putMapping("りゅ", "ryu"); putMapping("りょ", "ryo");

// 浊音/半浊音拗音
            putMapping("ぎゃ", "gya"); putMapping("ぎゅ", "gyu"); putMapping("ぎょ", "gyo");
            putMapping("じゃ", "ja");  putMapping("じゅ", "ju");  putMapping("じょ", "jo");
            putMapping("びゃ", "bya"); putMapping("びゅ", "byu"); putMapping("びょ", "byo");
            putMapping("ぴゃ", "pya"); putMapping("ぴゅ", "pyu"); putMapping("ぴょ", "pyo");

// 特殊组合（现代日语扩展）
            putMapping("ふぁ", "fa");  putMapping("ふぃ", "fi");  putMapping("ふぇ", "fe");  putMapping("ふぉ", "fo");
            putMapping("てぃ", "ti");  putMapping("とぅ", "tu");  putMapping("でゅ", "dyu");
            putMapping("うぃ", "wi");  putMapping("うぇ", "we");  putMapping("うぉ", "wo");
            putMapping("ヴぁ", "va");  putMapping("ヴぃ", "vi");  putMapping("ヴ", "vu");  putMapping("ヴぇ", "ve");  putMapping("ヴぉ", "vo");
            putMapping("くぁ", "kwa"); putMapping("くぃ", "kwi"); putMapping("くぇ", "kwe"); putMapping("くぉ", "kwo");
            putMapping("つぁ", "tsa"); putMapping("つぃ", "tsi"); putMapping("つぇ", "tse"); putMapping("つぉ", "tso");

// 小写假名特殊处理
            putMapping("ぁ", "a"); putMapping("ぃ", "i"); putMapping("ぅ", "u"); putMapping("ぇ", "e"); putMapping("ぉ", "o");
            putMapping("ゃ", "ya"); putMapping("ゅ", "yu"); putMapping("ょ", "yo");
            putMapping("ゎ", "wa"); putMapping("ゕ", "ka"); putMapping("ゖ", "ke");
        }

        private static void putMapping(String kana, String romaji) {
            MAPPING.put(kana, romaji);
        }

        public static String getRomaji(String kana) {
            return MAPPING.get(kana);
        }
    }
}