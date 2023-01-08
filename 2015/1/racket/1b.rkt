#lang racket

(define input 
  (port->string (open-input-file "../1.input") #:close?  #t))

(define (entered-basement string)
  (define count 0)
  (define ordinal 1)
  (for ([char (in-string string)])
    (cond
      [(char=? char #\() (set! count (+ count 1))]
      [(char=? char #\)) (set! count (- count 1))])
    #:break (= count -1)
    (set! ordinal (+ ordinal 1)))
  ordinal)

(entered-basement input)