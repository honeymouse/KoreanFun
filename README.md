# KoreanFun
Everything in this repository is under the MIT license. As such, you are pretty much free to do anything you want with it.
If you see any potential improvements or optimizations, please do comment!


Honorifier.java - This was an experiment with the Korean unicode. The precomposed hangul syllables in the Hangul Syllables block in Unicode are algorithmically defined, using the following formula: [(initial) × 588 + (medial) × 28 + (final)] + 44032. With this, the formula for the final consonant (받침) was easily derived.

Korean-Shiritori (끝말잇기) is a Korean word game in which the players are required to say a word which begins with the final hangul block of the previous word. Ex) 사과 -> 과일 -> 일상. This educational mini-project was created with supporting house rules and server-client relationships in mind. As such, the code in the main method was kept to the minimum while attempting to realistically replicate client-side commands and IOs (ex. Discord bots). (The the dual sound house rule is included.)
