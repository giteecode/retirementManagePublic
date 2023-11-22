module.exports = {
  root: true,
  env: {
    node: true,
    /** 解决defineProps没有导入的警告 */
    "vue/setup-compiler-macros": true
  },
  extends: [
    "plugin:vue/vue3-essential",
    "eslint:recommended",
    "@vue/typescript/recommended",
    "plugin:prettier/recommended"
  ],
  parserOptions: {
    ecmaVersion: 2020
  },
  rules: {
    "linebreak-style": [0, "error", "windows"],
    "no-console": process.env.NODE_ENV === "production" ? "warn" : "off",
    "no-debugger": process.env.NODE_ENV === "production" ? "warn" : "off",
    indent: 0,
    "space-before-function-paren": 0,
    "vue/multi-word-component-names": "off",
    "@typescript-eslint/no-explicit-any": "off",
    "vue/no-mutating-props": "off", // 不允许组件 prop的改变
    "prettier/prettier": "off"
  }
};
