(defn find-max-crossing-subarray [A low mid high] 
  (if (or (= low mid) (= mid high)) nil
    (let [coll (take (inc high) (drop low A)) 
          left (take (inc (- mid low)) coll) 
          right (take-last (- high mid) coll)]
      (#(list (first %) (second %) (apply + (map (partial nth %) [2 3])))
         (apply interleave
                (map (partial apply (partial max-key second)) 
                     (list (for [i (range (inc (- mid low)))]
                             (list (+ low i) (apply + (drop i left))))
                           (for [i (range (- high mid))]
                             (list (- high i) (apply + (drop-last i right))))))))))) 

(defn find-maximum-subarray [A low high]
  (if (= high low) 
    (list low high (nth A low))
    (let [mid (int (/ (+ low high) 2))]
      (apply (partial max-key #(nth % 2)) 
             (remove nil?  
                     (list (find-maximum-subarray A low mid) 
                           (find-maximum-subarray A (inc mid) high) 
                           (find-max-crossing-subarray A low mid high)))))))
