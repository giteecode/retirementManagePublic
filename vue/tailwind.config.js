/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./public/**/*.html', './src/**/*.{js,jsx,ts,tsx,vue}'],
  theme: {
    // colors: {
    //   transparent: 'transparent',
    //   current: 'currentColor',
    //   blue: {
    //     light: '#79bbff',
    //     DEFAULT: '#409eff',
    //     dark: '#337ecc'
    //   }
    // }
    extend: {
      colors: {
        transparent: 'transparent',
        current: 'currentColor',
        blue: {
          light: '#79bbff',
          DEFAULT: '#409eff',
          dark: '#337ecc'
        }
      }
    }
  },
  plugins: []
}
