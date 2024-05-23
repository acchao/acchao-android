package com.acchao.portfolio

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PortfolioRepositoryTest {
//    private lateinit var context: Context
//    private lateinit var repository: PortfolioRepository
//
//    @Before
//    fun setUp() {
//        context = ApplicationProvider.getApplicationContext()
//        repository = PortfolioRepository(context)
//    }
//
//    @Test
//    fun `test hasSeenOnboarding returns false if not set`() = runBlocking {
//        val hasSeenOnboarding = repository.hasSeenOnboarding().first()
//        assertFalse(hasSeenOnboarding)
//    }
//
//    @Test
//    fun `test setHasSeenOnboarding sets the value correctly`() = runBlocking {
//        repository.setHasSeenOnboarding(true)
//        val hasSeenOnboarding = repository.hasSeenOnboarding().first()
//        assertTrue(hasSeenOnboarding)
//
//        repository.setHasSeenOnboarding(false)
//        val hasSeenOnboardingAgain = repository.hasSeenOnboarding().first()
//        assertFalse(hasSeenOnboardingAgain)
//    }
//
//    @Test
//    fun `test isInternetAvailable returns true for wifi on API 23+`() {
//        val connectivityManager = mock(ConnectivityManager::class.java)
//        val networkCapabilities = mock(NetworkCapabilities::class.java)
//
//        `when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
//        `when`(connectivityManager.activeNetwork).thenReturn(mock(NetworkCapabilities::class.java))
//        `when`(connectivityManager.getNetworkCapabilities(any())).thenReturn(networkCapabilities)
//        `when`(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)).thenReturn(true)
//
//        val result = repository.isInternetAvailable(context)
//        assertTrue(result)
//    }
//
//    @Test
//    fun `test isInternetAvailable returns false when no network is available on API 23+`() {
//        val connectivityManager = mock(ConnectivityManager::class.java)
//
//        `when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
//        `when`(connectivityManager.activeNetwork).thenReturn(null)
//
//        val result = repository.isInternetAvailable(context)
//        assertFalse(result)
//    }
//
//    @Test
//    fun `test getPortfolio emits portfolio`() = runBlockingTest {
//        val portfolio = repository.getPortfolio().first()
//        assertNotNull(portfolio)
//    }
}