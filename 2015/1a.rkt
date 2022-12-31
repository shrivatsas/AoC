#lang racket

(define input 
  (port->string (open-input-file "1.input") #:close?  #t))

(define (count-brackets string)
  (define count 0)
  (for ([char (in-string string)])
    (cond
      [(char=? char #\() (set! count (+ count 1))]
      [(char=? char #\)) (set! count (- count 1))]))
  count)

(count-brackets input)