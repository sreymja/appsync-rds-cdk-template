package com.pennyhill.introspect;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.regex.Pattern;

public class Utils {

    public static String singularCapitalize(String plural) throws Exception {
        var str = singular(plural);
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    static String singular(String plural) throws Exception {
        if (unCountable().contains(plural.toLowerCase())) {
            return plural;
        }
        var exceptions = exceptions().stream().filter(e -> e.getPlural().equals(plural)).toList();

        if (!exceptions.isEmpty()) {
            return exceptions.get(exceptions.size() -1).single;
        }
        var endsWith = exceptions().stream().filter(p -> plural.endsWith(p.getPlural())).toList();

        if (!endsWith.isEmpty()) return plural.replace(endsWith.get(endsWith.size()-1).plural, endsWith.get(endsWith.size()-1).single);

        try {
            var matches = singularRules().stream().filter(p ->
                        Pattern.compile(p.pluralPat, Pattern.CASE_INSENSITIVE).matcher(plural).find()
                    ).toList();
            if(matches.isEmpty()) return plural;
            return Pattern.compile(matches.get(matches.size()-1).pluralPat, Pattern.CASE_INSENSITIVE).matcher(plural).replaceAll(matches.get(matches.size()-1).singlePat);
        } catch(IllegalArgumentException ex) {
            throw new Exception("Can't singularize this word, could not find a rule to match.");
        }
    }

    private static List<RulePattern> singularRules(){
        return List.of(
                new RulePattern("s$" , ""),
                new RulePattern("(s|si|u)s$" , "$1s"),
                new RulePattern("(n)ews$" , "$1ews"),
                new RulePattern("([ti])a$" , "$1um"),
                new RulePattern("((a)naly|(b)a|(d)iagno|(p)arenthe|(p)rogno|(s)ynop|(t)he)ses$" , "$1$2sis"),
                new RulePattern("(^analy)ses$" , "$1sis"),
                new RulePattern("(^analy)sis$" , "$1sis"),
                new RulePattern("([^f])ves$" , "$1fe"),
                new RulePattern("(hive)s$" , "$1"),
                new RulePattern("(tive)s$" , "$1"),
                new RulePattern("([lr])ves$" , "$1f"),
                new RulePattern("([^aeiouy]|qu)ies$" , "$1y"),
                new RulePattern("(s)eries$" , "$1eries"),
                new RulePattern("(m)ovies$" , "$1ovie"),
                new RulePattern("(x|ch|ss|sh)es$" , "$1"),
                new RulePattern("([m|l])ice$" , "$1ouse"),
                new RulePattern("(bus)es$" , "$1"),
                new RulePattern("(o)es$" , "$1"),
                new RulePattern("(shoe)s$" , "$1"),
                new RulePattern("(cris|ax|test)is$" , "$1is"),
                new RulePattern("(cris|ax|test)es$" , "$1is"),
                new RulePattern("(octop|vir)i$" , "$1us"),
                new RulePattern("(octop|vir)us$" , "$1us"),
                new RulePattern("(alias|status)es$" , "$1"),
                new RulePattern("(alias|status)$" , "$1"),
                new RulePattern("^(ox)en" , "$1"),
                new RulePattern("(vert|ind)ices$" , "$1ex"),
                new RulePattern("(matr)ices$" , "$1ix"),
                new RulePattern("(quiz)zes$" , "$1"),
                new RulePattern("a$" , "um"),
                new RulePattern("i$" , "us"),
                new RulePattern("ae$" , "a")
        );
    }

    private static List<String> unCountable() {
        return List.of("equipment", "information", "rice", "money",
                "species", "series", "fish", "sheep", "aircraft", "bison",
                "flounder", "pliers", "bream",
                "gallows", "proceedings", "breeches", "graffiti", "rabies",
                "britches", "headquarters", "salmon", "carp", "herpes",
                "scissors", "chassis", "high-jinks", "sea-bass", "clippers",
                "homework", "cod", "innings", "shears",
                "contretemps", "jackanapes", "corps", "mackerel",
                "swine", "debris", "measles", "trout", "diabetes", "mews",
                "tuna", "djinn", "mumps", "whiting", "eland", "news",
                "wildebeest", "elk", "pincers", "sugar");
    }

    private static List<RuleException>  exceptions() {
        //noinspection RedundantTypeArguments (explicit type arguments speedup compilation and analysis time)
        return List.of(
                new RuleException("person", "people"),
                new RuleException("man", "men"),
                new RuleException("goose", "geese"),
                new RuleException("child", "children"),
                new RuleException("sex", "sexes"),
                new RuleException("move", "moves"),
                new RuleException("stadium", "stadiums"),
                new RuleException("deer", "deer"),
                new RuleException("codex", "codices"),
                new RuleException("murex", "murices"),
                new RuleException("silex", "silices"),
                new RuleException("radix", "radices"),
                new RuleException("helix", "helices"),
                new RuleException("alumna", "alumnae"),
                new RuleException("alga", "algae"),
                new RuleException("vertebra", "vertebrae"),
                new RuleException("persona", "personae"),
                new RuleException("stamen", "stamina"),
                new RuleException("foramen", "foramina"),
                new RuleException("lumen", "lumina"),
                new RuleException("afreet", "afreeti"),
                new RuleException("afrit", "afriti"),
                new RuleException("efreet", "efreeti"),
                new RuleException("cherub", "cherubim"),
                new RuleException("goy", "goyim"),
                new RuleException("human", "humans"),
//                new Exception("lumen", "lumina"),
                new RuleException("seraph", "seraphim"),
                new RuleException("Alabaman", "Alabamans"),
                new RuleException("Bahaman", "Bahamans"),
                new RuleException("Burman", "Burmans"),
                new RuleException("German", "Germans"),
                new RuleException("Hiroshiman", "Hiroshimans"),
                new RuleException("Liman", "Limans"),
                new RuleException("Nakayaman", "Nakayamans"),
                new RuleException("Oklahoman", "Oklahomans"),
                new RuleException("Panaman", "Panamans"),
                new RuleException("Selman", "Selmans"),
                new RuleException("Sonaman", "Sonamans"),
                new RuleException("Tacoman", "Tacomans"),
                new RuleException("Yakiman", "Yakimans"),
                new RuleException("Yokohaman", "Yokohamans"),
                new RuleException("Yuman", "Yumans"),
                new RuleException("criterion", "criteria"),
                new RuleException("perihelion", "perihelia"),
                new RuleException("aphelion", "aphelia"),
                new RuleException("phenomenon", "phenomena"),
                new RuleException("prolegomenon", "prolegomena"),
                new RuleException("noumenon", "noumena"),
                new RuleException("organon", "organa"),
                new RuleException("asyndeton", "asyndeta"),
                new RuleException("hyperbaton", "hyperbata"),
                new RuleException("foot", "feet")
        );
    }

    @Data
    @AllArgsConstructor
    private static class RuleException {
        private String plural;
        private String single;
    }

    @Data
    @AllArgsConstructor
    private static class RulePattern {
        private String pluralPat;
        private String singlePat;
    }
}
