# WordCount

Usage: java –jar WordCount.jar <URL>

Several assumptions were made in the design of this program:
- Word counting should be case insensitive.
- Words containing apostrophes should be considered as contiguous, but words separated by underscores, dashes, or any other punctuation should be considered distinct.
- The program should only parse HTML documents. It cannot process text files.
- Primarily english language text will be used. While the program should recognize alphabetical characters from other languages, it may not properly handle other languages' nuances.

The program has several limitations:
- The web page’s title will be processed as well, despite not appearing in the body of the page. This is due to the design of the Jsoup.connect().get().text() method.
- The program does not exclude words containing only apostrophes. Therefore, text such as " ' " or "5'" will yield the word "'". This would be an easy but probably unnecessary fix. Furthermore, singly quoted words, such as ‘hello’ will be stored with their quotes.

The main challenge I faced in the implementation of the program was choosing a regular expression for the split() function which allowed the use of apostrophes. This was primarily because the apostrophes I encountered on most webpages were different characters than the ones on my keyboard (ASCII 92h rather than 27h). I also found that my program threw a MalformedURLException when text was not preceded by a protocol, due to error handling in the Jsoup library. Therefore, before throwing an error, the program tries resubmitting the request with a prepended “https://”. 

Testing was also difficult. Comparison with the web browser was difficult due to differences in page presentation, advertisements, and other data on the different platforms. However, simple text pages allowed for a better comparison, and allowed me to determine which HTML tags were being included.