(defn insert-into [coll element]
  (apply concat 
         (if (> (first coll) element)
           (list [element] coll)
           ((juxt first (fn [c] (list element)) second) 
              (partition-by #(< % element) coll)))))

(defn insert-sort [coll]
  (loop [to-sort (rest coll) 
         result (list (first coll))]
    (if (empty? to-sort) 
      result
      (recur (rest to-sort) 
             (insert-into result (first to-sort)))))) 
