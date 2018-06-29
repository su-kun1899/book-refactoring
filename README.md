#book-refactoring

## 第2章 リファクタリングの原則

### リファクタリングの定義

- 名詞
  - 外部から見たときの振る舞いを保ちつつ、理解や修正が簡単になるように、ソフトウェアの内部構造を変化させること
- 動詞
  - 一連のリファクタリングを適用して、外部から見た振る舞いの変更なしに、ソフトウェアを再構築すること
- あくまでソフトウェアを理解しやすく、変更を容易にするために行う
- ソフトウェアの外的振る舞いを保つことで、変更があったことに気づかない

#### 2つの帽子

- 機能追加とリファクタリングを区別する
- 常にどちらの帽子をかぶっているのか意識する

### リファクタリングを行う理由

- 設計の改善
- ソフトウェアが理解しやすくなる
- バグを見つけ出す
  - KentBeck「偉大なプログラマではなく、偉大な習慣を身に付けたプログラマ」
- より速くプログラミングできる
  - ラピッドな開発には優れた設計が不可欠である

### いつリファクタリングをすべきか

- 時間を設けて行うようなものではない
- 作業の中で要求が自然に発生するもの
- 3度目の法則
  - 3度目に似たようなことをしていると気付いたら
- 機能追加の時に行う
  - リファクタリングで機能追加を容易にする
- バグフィックスの時に行う
  - コードを理解するため
  - バグレポートをリファクタリングが必要な兆候とも考えられる
- コードレビューの時に行う
  - アイデアの結果を「実際に見る」ことができる
  - 少人数で行うべき
    - XPにおけるペアプロ

### 管理者を説得するには

- 品質を気にしている管理者
  - 品質の側面を強調する
  - レビューの過程でリファクタライングを採用する
  - レビューはバグを減らし、開発速度を上げるという多くの研究がある
- スケジュールを気にしている管理者
  - 黙ってやる
  - 最も速い方法がリファクタリング

### リファクタリングの問題点

- データベース
  - データの移行が必ずつきまとう
  - オブジェクトモデルとデータベースモデルの間に分離する層を用意する
  - 中間層は後から用意しても良い
- インターフェースの変更
  - 呼び出し元がすべて特定できるような場合大きな問題とはならない
  - 問題は公布済みインターフェース
  - 一定期間は両方のインターフェースを維持する
    - 古い実装で新しい実装を呼ぶようにする
    - Javaは `@Deprecated` が活用できる
  - 例外はパッケージレベルでの例外スーパークラスを用意すると柔軟になる
    - 公開メソッドではその例外をthrows宣言に使う
- リファクタリングしにくい設計
  - 設計の候補を並べて手間を考慮する
  - 容易に思われる場合はあまり検討せず単純な方法を採用
  - 困難な場合は時間をかけて検討する
- リファクタリングを避けるとき
  - 変更するより書き直したほうが早い場合
    - コードがほぼ正しく動作する水準に達している必要がある
     - カプセル化して、部分的にリファクタリングするという妥協案
      - レガシーシステムが重要な位置を占める場合には魅力的
  - 期間が迫っている場合
    - 生産性の向上が締め切り後に見られても遅い
    - 未着手のリファクタリングは借金であり、利子がある
    - リファクタリングを避けてはいけない
      - 時間が足りなくなるのは、リファクタリングが必要であるということ

### リファクタリングと設計

- リファクタリングを行う場合でも事前設計はある程度必要
- 設計が唯一無二の完璧な解決策を見出すものではなくなる
- 設計の簡素化
  - シンプルな解決策を柔軟なものにリファクタリングする
- 柔軟で複雑な設計というものは、ほとんどの場合、必要とされない

### リファクタリングとパフォーマンス

- リファクタリングが実行速度を遅くするのは事実
- パフォーマンスしやすい形にはなる
- 最初にチューニングしやすい形に作って、段階的にチューニングを行う
- パフォーマンスでネックになるのはごく一部
- 開発の後半でプロファイラを実行し、ホットスポットのみチューニングする

## 第3章 コードの不吉な臭い

- リファクタリングをどのタイミングで適用するか
- 正確な基準は設けない
- 感覚を養っていくしかない

### 重複したコード

- どこで定義すれば最も意味があるか
- メソッドの抽出
- メソッドの引き上げ
- アルゴリズムの取替
- クラスの抽出

### 長すぎるメソッド

- ルーチンは長くなるほど理解しにくくなる
- 最もおすすめなのはメソッド名をわかりやすくすること
  - 意図を示す
- コメントは抽出の候補になり得る
  - 本来の意図をメソッド化
  - 一行でも価値がある
  - メソッドの抽出
- 一時変数が多い
  - 問い合わせによる一時変数の置き換え
- パラメータが多い
  - パラメータオブジェクトの導入
  - オブジェクトそのものの受け渡し
- 強力な武器
  - メソッドオブジェクトによるメソッドの置き換え
- 条件分岐やループも抽出対象
  - 条件記述の分解
  - ループ部分を取り出すか、ループ内部を独立させる

### 巨大なクラス

- インスタンス変数の持ちすぎ
  - クラスの抽出
  - 変数名に共通の接頭辞/接尾辞があるものはまとめられるかも
  - サブクラスの抽出
- 未使用のインスタンス変数
  - クラスの抽出とサブクラスの抽出の適用を何回か試す
- コード量の多すぎるクラスは重複したコードの温床
  - 重複部分を排除する
- インターフェースの抽出によってクラスを分割する
- クラスがGUIならデータと振る舞いを分離したドメインクラスに移す
  - 観察されるデータの複製

### 長すぎるパラメータリスト

- パラメータが多いと一つ一つが何を意味しているのか分かりづらくなる
  - オブジェクトを渡すようにする
- メソッドによるパラメータの置き換え
- オブジェクトそのものの受け渡し
- パラメータオブジェクトの導入
- 依存関係には注意が必要

### 変更の偏り

- 変更しなければならない場合、変更箇所を一つにしてそこだけを変更したい
- 1つのクラスが別々の理由で何度も変更される場合、変更の偏りが起きている
- クラスの抽出で変更の理由ごとにクラスをまとめていく

### 変更の分散

- 変更を行うたびにあちこちのクラスが少しずつ書き換わる
- 変更とクラスが1対1になるのが理想的
- 「メソッドの移動」や「フィールドの移動」を行い、変更箇所をまとめる
- 「クラスのインライン化」で分散した振る舞いをまとめる

### 特性の横恋慕

- 自分のクラスよりも他のクラスに興味を持つような場合は誤り
  - 「メソッドの移動」や「メソッドの抽出」してから「メソッドの移動」で正しい位置に移動
- メソッド内で様々なクラスのデータを参照している場合
  - 大部分のデータを持ったクラスを作成し、メソッドをそこに移動する
- StrategyやVisitorパターンで振る舞いのみを変更できるようにする
  - 少し回りくどい

### データの群れ

- 群れをなしたデータはオブジェクトとして一箇所にまとめるべき
  - クラスの抽出
  - パラメータオブジェクトの導入
  - オブジェクトそのものの受け渡し
- データの集まりからある要素を除外して意味をなすか
  - なさないなら、オブジェクト候補
- まずオブジェクトを定義することが前進
  -  特性の横恋慕を嗅ぎ分け、整理していく

### 基本データ型への執着

- 基本データ型とそれより大きなクラスとの境界を取り除く
- 小さなクラスを容易に定義できる
- オブジェクトによるデータ値の置き換え
- クラスによるタイプコードの置き換え
- サブクラスによるタイプコードの置き換え
- State/Startegyによるタイプコードの置き換え
- クラスの抽出
- パラメータオブジェクトの導入
- オブジェクトによる配列の置き換え

### スイッチ文

- スイッチ分は重複したコードを生み出す問題児
- 「メソッドの抽出」と「メソッドの移動」でクラスにメソッドを定義する
  - ポリモーフィズムを使えるようになる
- 「サブクラスによるタイプコードの書き換え」か「State/Startegyによるタイプコードの書き換え」
- 継承構造があれば「ポリモーフィズムによる条件記述の置き換え」
- 条件が増えないような場合
  - 「明示的なメソッド群によるパラメータの置き換え」
- null値をを見ている場合は「ヌルオブジェクトの導入」
