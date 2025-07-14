# --- ViewModel ---
# 保留 ViewModel 類別與相關方法（避免反射失效）
-keep class androidx.lifecycle.ViewModel { *; }
-keepclassmembers class * {
    @androidx.lifecycle.* <methods>;
}

# --- Jetpack Compose ---
# 保留 Compose 所需的類別與註解
-keep class androidx.compose.** { *; }

# --- Network (Ktor or Retrofit) ---
# 若你使用 ktor client 呼叫 Supabase：
-keep class io.ktor.** { *; }

# --- Kotlinx Serialization ---
# 若你用 kotlinx.serialization 解 JSON：
-keep class kotlinx.serialization.** { *; }
-keepnames class kotlinx.serialization.** { *; }

# --- 保留泛型與注解資訊 ---
-keepattributes Signature
-keepattributes *Annotation*