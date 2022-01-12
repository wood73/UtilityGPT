# UtilityGPT
A Utility for another repository which is an OpenAI GPT-3 Api Client in Java - https://github.com/TheoKanning/openai-java

Enables easier queries to GPT-3 - all data except GPT-3's returned text (including prompt) is currently omitted.

Basic usage example:
```
String apiKey = "", model = "davinci", prompt = "Java is";
int tokens = 50;
String gptResponse = UtilityGPT.query(apiKey, model, prompt, tokens);
```

Can also set the global apiKey variable, and omit sending it as a parameter:
```
UtilityGPT.apiKey = "";

String model = "davinci", prompt = "Java is";
int tokens = 50;
String gptResponse = UtilityGPT.query(model, prompt, tokens);
```


-----------------------------------------------------------------------

Meaning of each parameter in all overloaded query methods:
----------------------------------------------------
     * @param apiKey (String), your GPT-3 API key
     * @param model (String), model to use (ada, babbage, curie, davinci | ada-instruct-beta, babbage-instruct-beta,
     *              curie-instruct-beta-v2, davinci-instruct-beta-v3
     * @param prompt (String), Prompt sent to the language model
     * @param tokens (int), Number of tokens that the language model will use, between both prompt/context length and generation length
     * @param temperature, (default 1) a value 0-1 with 1 being very creative, 0 being very factual/deterministic
     * @param topP, (default 1) between 0-1 where 1.0 means "use all tokens in the vocabulary" while 0.5 means "use only the 50% most common tokens"
     * @param frequencyPenalty (double), (default 0) 0-1, lowers the chances of a word being selected again the more times that word has already been used
     * @param presencePenalty (double), (default 0) 0-1, lowers the chances of topic repetition
     * @param bestOf (int), (default 1), queries GPT-3 this many times, then selects the 'best' generation to return
     * @param cutOffLastPunctuationMark (boolean), whether GPT-3's full output should be cut off at the last detected punctuation mark
     * @param stopSequence, String that GPT-3 will stop generating after
     * @param stopSequences, (1 - 4 elements inclusive) List of Strings, each of which GPT-3 will stop generating after
     

------------------------------------------------------

List of each method's parameters (each method has a duplicate without the apiKey parameter, which use the global apiKey var):
----------------------------------------------------
- query(String apiKey, String model, String prompt, int tokens)
- query(String apiKey, String model, String prompt, int tokens, String stopSequence)
- query(String apiKey, String model, String prompt, int tokens, List\<String\> stopSequences)
- query(String apiKey, String model, String prompt, int tokens, boolean cutOffLastPunctuationMark)
-----------------------------------------------------
- query(String apiKey, String model, String prompt, int tokens, double temperature)
- query(String apiKey, String model, String prompt, int tokens, double temperature, String stopSequence)
- query(String apiKey, String model, String prompt, int tokens, double temperature, List\<String\> stopSequences)
- query(String apiKey, String model, String prompt, int tokens, double temperature, boolean cutOffLastPunctuationMark)
-----------------------------------------------------
- query(String apiKey, String model, String prompt, int tokens, double temperature, double topP)
- query(String apiKey, String model, String prompt, int tokens, double temperature, double topP, String stopSequence)
- query(String apiKey, String model, String prompt, int tokens, double temperature, double topP, List\<String\> stopSequences)
- query(String apiKey, String model, String prompt, int tokens, double temperature, double topP, boolean cutOffLastPunctuationMark)
-----------------------------------------------------
- query(String apiKey, String model, String prompt, int tokens, double temperature, double topP, double frequencyPenalty, double presencePenalty)
- query(String apiKey, String model, String prompt, int tokens, double temperature, double topP, double frequencyPenalty, double presencePenalty, String stopSequence)
- query(String apiKey, String model, String prompt, int tokens, double temperature, double topP, double frequencyPenalty, List\<String\> stopSequences)
- query(String apiKey, String model, String prompt, int tokens, double temperature, double topP, double frequencyPenalty, double presencePenalty, boolean cutOffLastPunctuationMark)
-----------------------------------------------------
- query(String apiKey, String model, String prompt, int tokens, double temperature, double topP, double frequencyPenalty, double presencePenalty, int bestOf)
- query(String apiKey, String model, String prompt, int tokens, double temperature, double topP, double frequencyPenalty, double presencePenalty, int bestOf, String stopSequence)
- query(String apiKey, String model, String prompt, int tokens, double temperature, double topP, double frequencyPenalty, int bestOf, List\<String\> stopSequences)
- query(String apiKey, String model, String prompt, int tokens, double temperature, double topP, double frequencyPenalty, double presencePenalty, int bestOf, boolean cutOffLastPunctuationMark)
-----------------------------------------------------
-----------------------------------------------------


There is a boolean global variable called `automaticallyIncludePromptTokens`, it sets whether the tokens parameter should only refer to new text generated by GPT-3, and not the prompt; it is initialized to false.  If true, then Tokens will be automatically increased by (roughly) the # of tokens in the prompt.  The calculation for getting the number of tokens in the prompt, is dividing the number of chars by 4.  I find this calculation isn't super accurate, and overestimates the true number of tokens, resulting in some extra generated text.  https://help.openai.com/en/articles/4936856-what-are-tokens-and-how-to-count-them
```
//false by default
UtilityGPT.automaticallyIncludePromptTokens = true;
```
