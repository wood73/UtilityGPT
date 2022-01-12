import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;

import java.util.ArrayList;
import java.util.List;

/** A Utility class for the OpenAI GPT-3 Api Client in Java - https://github.com/TheoKanning/openai-java
 *  Enables easier queries to the language model
 */
public class UtilityGPT {

    /** counter for how many language model queries have occurred, and tokens used */
    public static int queryCounter = 0, tokenCounter = 0;

    /** whether the query count & token count should be continually printed to console */
    public static boolean printQueryCounter = false;


    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     *                on average 1.4 tokens are used per word
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param topP, 0-1 | 1.0 means "use all tokens in the vocabulary" while 0.5 means "use only the 50% most common tokens"
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, double topP) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            completionRequestBuilder.topP(topP);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param topP, 0-1 | 1.0 means "use all tokens in the vocabulary" while 0.5 means "use only the 50% most common tokens"
     * @param frequencyPenalty, (default 0) 0-1, lowers the chances of a word being selected again the more times that word has already been used
     * @param presencePenalty, (default 0) 0-1, lowers the chances of topic repetition
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, double topP, double frequencyPenalty,
                                            double presencePenalty) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            completionRequestBuilder.topP(topP);
            completionRequestBuilder.frequencyPenalty(frequencyPenalty);
            completionRequestBuilder.presencePenalty(presencePenalty);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param topP, 0-1 | 1.0 means "use all tokens in the vocabulary" while 0.5 means "use only the 50% most common tokens"
     * @param frequencyPenalty, (default 0) 0-1, lowers the chances of a word being selected again the more times that word has already been used
     * @param presencePenalty, (default 0) 0-1, lowers the chances of topic repetition
     * @param bestOf, (default 1), queries GPT-3 this many times, then selects the 'best' generation to return
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, double topP, double frequencyPenalty,
                                            double presencePenalty, int bestOf) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            completionRequestBuilder.topP(topP);
            completionRequestBuilder.frequencyPenalty(frequencyPenalty);
            completionRequestBuilder.presencePenalty(presencePenalty);
            completionRequestBuilder.bestOf(bestOf);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

//####################################################################################################################
//####################################################################################################################


    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     *                on average 1.4 tokens are used per word
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param stopSequence, String that GPT-3 will stop generating after
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model, String stopSequence) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            List<String> stopSequenceList = new ArrayList<>();
            stopSequenceList.add(stopSequence);
            completionRequestBuilder.stop(stopSequenceList);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param stopSequence, String that GPT-3 will stop generating after
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, String stopSequence) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            List<String> stopSequenceList = new ArrayList<>();
            stopSequenceList.add(stopSequence);
            completionRequestBuilder.stop(stopSequenceList);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param topP, 0-1 | 1.0 means "use all tokens in the vocabulary" while 0.5 means "use only the 50% most common tokens"
     * @param stopSequence, String that GPT-3 will stop generating after
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, double topP, String stopSequence) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            completionRequestBuilder.topP(topP);
            List<String> stopSequenceList = new ArrayList<>();
            stopSequenceList.add(stopSequence);
            completionRequestBuilder.stop(stopSequenceList);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param topP, 0-1 | 1.0 means "use all tokens in the vocabulary" while 0.5 means "use only the 50% most common tokens"
     * @param frequencyPenalty, (default 0) 0-1, lowers the chances of a word being selected again the more times that word has already been used
     * @param presencePenalty, (default 0) 0-1, lowers the chances of topic repetition
     * @param stopSequence, String that GPT-3 will stop generating after
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, double topP, double frequencyPenalty,
                                            double presencePenalty, String stopSequence) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            completionRequestBuilder.topP(topP);
            completionRequestBuilder.frequencyPenalty(frequencyPenalty);
            completionRequestBuilder.presencePenalty(presencePenalty);
            List<String> stopSequenceList = new ArrayList<>();
            stopSequenceList.add(stopSequence);
            completionRequestBuilder.stop(stopSequenceList);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param topP, 0-1 | 1.0 means "use all tokens in the vocabulary" while 0.5 means "use only the 50% most common tokens"
     * @param frequencyPenalty, (default 0) 0-1, lowers the chances of a word being selected again the more times that word has already been used
     * @param presencePenalty, (default 0) 0-1, lowers the chances of topic repetition
     * @param bestOf, (default 1), queries GPT-3 this many times, then selects the 'best' generation to return
     * @param stopSequence, String that GPT-3 will stop generating after
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, double topP, double frequencyPenalty,
                                            double presencePenalty, int bestOf, String stopSequence) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            completionRequestBuilder.topP(topP);
            completionRequestBuilder.frequencyPenalty(frequencyPenalty);
            completionRequestBuilder.presencePenalty(presencePenalty);
            completionRequestBuilder.bestOf(bestOf);
            List<String> stopSequenceList = new ArrayList<>();
            stopSequenceList.add(stopSequence);
            completionRequestBuilder.stop(stopSequenceList);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

//####################################################################################################################
//####################################################################################################################

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     *                on average 1.4 tokens are used per word
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param stopSequence, List of Strings which GPT-3 will stop generating after
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model, List<String> stopSequence) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.stop(stopSequence);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param stopSequence, List of Strings which GPT-3 will stop generating after
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, List<String> stopSequence) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            completionRequestBuilder.stop(stopSequence);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param topP, 0-1 | 1.0 means "use all tokens in the vocabulary" while 0.5 means "use only the 50% most common tokens"
     * @param stopSequence, List of Strings which GPT-3 will stop generating after
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, double topP, List<String> stopSequence) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            completionRequestBuilder.topP(topP);
            completionRequestBuilder.stop(stopSequence);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param topP, 0-1 | 1.0 means "use all tokens in the vocabulary" while 0.5 means "use only the 50% most common tokens"
     * @param frequencyPenalty, (default 0) 0-1, lowers the chances of a word being selected again the more times that word has already been used
     * @param presencePenalty, (default 0) 0-1, lowers the chances of topic repetition
     * @param stopSequence, List of Strings which GPT-3 will stop generating after
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, double topP, double frequencyPenalty,
                                            double presencePenalty, List<String> stopSequence) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            completionRequestBuilder.topP(topP);
            completionRequestBuilder.frequencyPenalty(frequencyPenalty);
            completionRequestBuilder.presencePenalty(presencePenalty);
            completionRequestBuilder.stop(stopSequence);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param topP, 0-1 | 1.0 means "use all tokens in the vocabulary" while 0.5 means "use only the 50% most common tokens"
     * @param frequencyPenalty, (default 0) 0-1, lowers the chances of a word being selected again the more times that word has already been used
     * @param presencePenalty, (default 0) 0-1, lowers the chances of topic repetition
     * @param bestOf, (default 1), queries GPT-3 this many times, then selects the 'best' generation to return
     * @param stopSequence, List of Strings which GPT-3 will stop generating after
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, double topP, double frequencyPenalty,
                                            double presencePenalty, int bestOf, List<String> stopSequence) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            completionRequestBuilder.topP(topP);
            completionRequestBuilder.frequencyPenalty(frequencyPenalty);
            completionRequestBuilder.presencePenalty(presencePenalty);
            completionRequestBuilder.bestOf(bestOf);
            completionRequestBuilder.stop(stopSequence);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), false);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

//####################################################################################################################
//####################################################################################################################

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     *                on average 1.4 tokens are used per word
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param stopAtPunctuationMark, whether GPT-3's full output should be cut off at the last detected punctuation mark
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model, boolean stopAtPunctuationMark) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), stopAtPunctuationMark);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param stopAtPunctuationMark, whether GPT-3's full output should be cut off at the last detected punctuation mark
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, boolean stopAtPunctuationMark) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), stopAtPunctuationMark);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param topP, 0-1 | 1.0 means "use all tokens in the vocabulary" while 0.5 means "use only the 50% most common tokens"
     * @param stopAtPunctuationMark, whether GPT-3's full output should be cut off at the last detected punctuation mark
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, double topP, boolean stopAtPunctuationMark) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            completionRequestBuilder.topP(topP);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), stopAtPunctuationMark);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param topP, 0-1 | 1.0 means "use all tokens in the vocabulary" while 0.5 means "use only the 50% most common tokens"
     * @param frequencyPenalty, (default 0) 0-1, lowers the chances of a word being selected again the more times that word has already been used
     * @param presencePenalty, (default 0) 0-1, lowers the chances of topic repetition
     * @param stopAtPunctuationMark, whether GPT-3's full output should be cut off at the last detected punctuation mark
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, double topP, double frequencyPenalty,
                                            double presencePenalty, boolean stopAtPunctuationMark) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            completionRequestBuilder.topP(topP);
            completionRequestBuilder.frequencyPenalty(frequencyPenalty);
            completionRequestBuilder.presencePenalty(presencePenalty);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), stopAtPunctuationMark);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

    /** Utility method for using the GPT-3 Java Api Client
     *
     * @param apiKey, your GPT-3 API key
     * @param context, Prompt sent to the language model
     * @param tokens, Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param model, model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3 https://beta.openai.com/docs/engines/instruct-series-beta)
     * @param temperature, a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param topP, 0-1 | 1.0 means "use all tokens in the vocabulary" while 0.5 means "use only the 50% most common tokens"
     * @param frequencyPenalty, (default 0) 0-1, lowers the chances of a word being selected again the more times that word has already been used
     * @param presencePenalty, (default 0) 0-1, lowers the chances of topic repetition
     * @param bestOf, (default 1), queries GPT-3 this many times, then selects the 'best' generation to return
     * @param stopAtPunctuationMark, whether GPT-3's full output should be cut off at the last detected punctuation mark
     * @return GPT-3's generated text
     */
    public static String queryLanguageModel(String apiKey, String context, int tokens, String model,
                                            double temperature, double topP, double frequencyPenalty,
                                            double presencePenalty, int bestOf, boolean stopAtPunctuationMark) {
        queryCounter++;
        tokenCounter += tokens;
        if(printQueryCounter) {
            System.out.println("---------------------------------------------\n" +
                    "# of GPT-3 queries: " + queryCounter + "\nTotal tokens used: " + tokenCounter + "\n" +
                    "---------------------------------------------");
        }

        try {
            OpenAiService service = new OpenAiService(apiKey);

            CompletionRequest.CompletionRequestBuilder completionRequestBuilder = CompletionRequest.builder()
                    .prompt(context)
                    .echo(true);
            completionRequestBuilder.maxTokens(tokens);
            completionRequestBuilder.temperature(temperature);
            completionRequestBuilder.topP(topP);
            completionRequestBuilder.frequencyPenalty(frequencyPenalty);
            completionRequestBuilder.presencePenalty(presencePenalty);
            completionRequestBuilder.bestOf(bestOf);

            CompletionRequest completionRequest = completionRequestBuilder.build();
            List<CompletionChoice> outputList = service.createCompletion(model, completionRequest).getChoices();

            String output = _apiOutputCondensed(outputList.get(0).toString(), stopAtPunctuationMark);

            return output;

        } catch(Exception e) {
            e.printStackTrace();
            return "Error thrown";
        }
    }

//####################################################################################################################
//####################################################################################################################


    /**
     *  Extracts the language model's raw output from the output of the GPT-3 Java Api Client
     *
     * @param output, what is outputted by an OpenAiService object calling the methods -
     *                createCompletion(model, completionRequest).getChoices().get(0).toString()
     * @param stopAtPunctuationMark, whether GPT-3's full output should be cut off at the last detected punctuation mark
     * @return Language model's raw output
     */
    public static String _apiOutputCondensed(String output, boolean stopAtPunctuationMark) {
        String cOut = output.toString();
        //start inclusive, end exclusive
        int endIndex = 0, startIndex = cOut.indexOf('=') + 1;
        for(int i = cOut.length() - 1, commaCount = 0; i >= 0 && endIndex == 0; i--) {
            if(cOut.charAt(i) == ',') {
                commaCount++;
            }
            if(commaCount == 3) {
                endIndex = i;
            }
        }
        String format = cOut.substring(startIndex, endIndex);
        if(stopAtPunctuationMark) {
            if(format.contains(".") || format.contains("!") || format.contains("?")) {
                //index will be -1 if char not found
                int periodIndex = format.lastIndexOf('.');
                int exclamationIndex = format.lastIndexOf('!');
                int questionIndex = format.lastIndexOf('?');

                if(periodIndex > exclamationIndex && periodIndex > questionIndex)
                    return format.substring(0, periodIndex + 1);
                else if(exclamationIndex > questionIndex)
                    return format.substring(0, exclamationIndex + 1);
                else
                    return format.substring(0, questionIndex + 1);
            }
        }

        return format;
    }

}
