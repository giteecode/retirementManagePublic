import { http } from '@/utils'
// 可售床位
export async function getAvailableBed() {
  return http.get('/api/home/availableBed')
}
// 业务趋势
export async function getBusinessTrend() {
  return http.get('/api/home/businessTrend')
}
// 客户来源渠道
export async function getClientSource() {
  return http.get('/api/home/clientSource')
}
// 本月业绩排行
export async function getMonthPerformanceRank() {
  return http.get('/api/home/monthPerformanceRank')
}
// 今日概览
export async function getTodayOverview() {
  return http.get('/api/home/todayOverview')
}
// 今日销售跟进
export async function getTodaySaleFollow() {
  return http.get('/api/home/todaySaleFollow')
}
