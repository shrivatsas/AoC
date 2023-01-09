#lang racket
; Read a file into a list of lines

(define (read-lines filename)
  (with-input-from-file filename
    (λ ()
      (let loop ([lines (list)])
        (let ([line (read-line)])
          (if (eof-object? line)
              (reverse lines)
              (loop (cons line lines))))))))

(define lines (read-lines "2.input"))

(define wrapper 0)
(for ([line lines])
  (define segments (string-split line "x"))
  (let-values ([(l w h) (apply values (map string->number segments))])
    (set! wrapper (+ wrapper (+ (* 2 l w) (* 2 w h) (* 2 h l) (min (* l w) (* l h) (* h w)))))))

(display wrapper)
