
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

public class CoreNlpExample {

    public static void main(String[] args){

        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing
        Properties prpt = new Properties();
        prpt.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(prpt);

        String text = "Vladimir Putin has been sworn in for a fourth term as president of Russia, after winning the election in March.\n" +
                "He has been in power for 18 years, whether as president or prime minister, and opponents have likened his tenure to the reign of a tsar, or emperor.\n" +
                "Russian TV showed Mr Putin, 65, leaving his Kremlin office and walking to the ceremony, down long corridors.\n" +
                "Riot police confronted protesters against his rule in Moscow and other Russian cities on Saturday.\n" +
                "More than 1,000 arrests are said to have been made in 19 cities.";

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for(CoreMap sentence: sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the text of the token
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                System.out.println(String.format("Print:word:[%s] pos:[%s] ne:[%s]", word, pos, ne));
            }
        }
    }

}
