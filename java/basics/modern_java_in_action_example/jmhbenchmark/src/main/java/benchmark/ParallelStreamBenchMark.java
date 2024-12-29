package benchmark;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


@State(Scope.Benchmark) // 동일 테스트 내 모든 Thread에서 동일 Instance를 공유 (Multi-Threading 테스트)
@BenchmarkMode(Mode.AverageTime) // 벤치마크 대상 메서드를 실행하는 데 걸린 평균 시간 측정
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 결과를 밀리초 단위로 출력
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"}) // 4Gb 힙 공간을 제공한 환경에서 두 번 벤치마크를 수행해 결과의 신뢰성 확보
public class ParallelStreamBenchMark {
	private static final long N = 100_000_000L;

	@Benchmark
	public long sequentialSum(){
		return Stream.iterate(1L, i -> i+1)
			.limit(N)
			.reduce(0L, Long::sum);
	}

	@Benchmark
	public long iterativeSum(){
		long result =0;
		for(long i =1L; i<=N; i++){
			result += i;
		}
		return result;
	}

	@Benchmark
	public long parallelSum(){
		return Stream.iterate(1L, i -> i+1)
			.limit(N)
			.parallel()
			.reduce(0L, Long::sum);
	}

	@Benchmark
	public long rangedSum(){
		return LongStream.rangeClosed(1, N)
			.reduce(0L, Long::sum);
	}

	@Benchmark
	public long parallelRangedSum(){
		return LongStream.rangeClosed(1, N)
			.parallel()
			.reduce(0L, Long::sum);
	}

	@TearDown(Level.Invocation) // 매 번 벤치마크를 실행한 다음에는 가비지 컬렉터 동작 시도 (@State 명시 필수)
	public void tearDown(){
		System.gc();
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
			.include(ParallelStreamBenchMark.class.getSimpleName())
			.forks(1)
			.build();

		new Runner(opt).run();
	}


}
