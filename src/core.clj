(ns core
  (:require
    [bips.bip32 :as bip32]
    [bips.bip32-utils :as bip32-utils]
    [bips.bip39 :as bip39]))


(defn- main
  []
  (let [mnemonic "acid employ suggest menu desert pioneer hard salmon consider stuff margin over bus fiction direct useful tornado output forward wing cute chicken ladder hockey"]
    (clojure.pprint/pprint
      (bip32-utils/serialize-base58
        :mainnet :private 3
        (bip32-utils/key-fingerprint
          (:public-key
            (bip32/derive-path
              (bip39/mnemonic->seed mnemonic) "m/44H/0H" :public)))
        (bip32-utils/hardened 0)
        (:chain-code (bip32/derive-path
                       (bip39/mnemonic->seed mnemonic) "m/44H/0H/0H" :private))
        (:private-key (bip32/derive-path
                        (bip39/mnemonic->seed mnemonic) "m/44H/0H/0H" :private))))))
