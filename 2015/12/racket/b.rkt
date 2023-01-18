#lang racket

(require json)

(define input 
  (port->string (open-input-file "../12.input") #:close?  #t))

(define (sum-of-numbers-helper json-obj)
  (cond
    [(number? json-obj) json-obj]
    [(list? json-obj) (apply + (map sum-of-numbers-helper json-obj))]
    [(dict? json-obj) 
     (if (member "red" (map (lambda (key) (dict-ref json-obj key)) (dict-keys json-obj)))
         0
         (apply + (map (lambda (key) (sum-of-numbers-helper (dict-ref json-obj key))) (dict-keys json-obj))))]
    [else 0]))

(define (sum-of-numbers input)
  (define json-obj (string->jsexpr input))
  (sum-of-numbers-helper json-obj))

(display (sum-of-numbers input))