# Why Lox.

Lox is an exploration of Programming Language Theory (PLT) and Linguistics itself. For example we are interested in Grammar and all of its types; also we are interested in possibilities of different grammars and all of their forms -- Think Noam Chomsky!

However, all of this is through the aid of Robert Nystrom who is a language expert at Google, and on his book the aspiring giant of the author stands. Primarily, we focus on programming language theory because of Robert. And then we move on to the main project. Appended to the end of this document are questions and contentions this author is interested in.

## Questions
1) The lexixal grammars of Python and Haskell are not regular. What does that mean, and why aren't they?

2) Aside from separating tokens -- distinguishing `print foo` from `printfoo` -- spaces aren't used for much in most languages. However, in a couple of dark corners, a space does affect how code is parsed in CoffeeScript, Ruby and the C preprocessor. Where and what effect does it have in each of those languages?

3) Our scanner here, like most, discards comments and whitespaces since those aren't needed by the parser. Why might you want to write a scanner that does not discard those? What would it be useful for?
