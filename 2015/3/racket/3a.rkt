#lang racket

(define walk (string-append (file->string "../3.input")))

(define (stringify pos)
  (string-append (number->string (car pos)) (number->string (cdr pos))))

(define pos (cons 0 0))

(define visited (set))
(set! visited (set-add visited (stringify pos)))

(for ([c walk])
  (cond
    [(char=? c #\^) (set! pos (cons (car pos) (+ (cdr pos) 1)))]
    [(char=? c #\v) (set! pos (cons (car pos) (- (cdr pos) 1)))]
    [(char=? c #\>) (set! pos (cons (+ (car pos) 1) (cdr pos)))]
    [(char=? c #\<) (set! pos (cons (- (car pos) 1) (cdr pos)))]
    [else (error "Unexpected character" c)])
  (set! visited (set-add visited (stringify pos))))

(displayln (set-count visited))