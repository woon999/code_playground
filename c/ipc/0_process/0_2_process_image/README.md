# 프로세스 이미지
프로세스 이미지는 프로그램 실행 시 필요한 실행 파일이다. 이 이미지는 일반적으로 다음 섹션을 포함한다.
- 코드 세그먼트 또는 텍스트 세그먼트 (크기 고정)
    - 실행 가능한 명령으로 구성된 object 파일 또는 프로그램의 가상 주소 공간의 일부
    - 이것은 일반적으로 읽기 전용 데이터 세그먼트이며 크기가 고정되어 있음
- 데이터 세그먼트 (크기 고정)
    - 초기화된 데이터 세그먼트: 정적 및 전역 변수로 구성된 object 파일 또는 프로그램의 가상 주소 공간의 일부
    - 초기화되지 않은 데이터 세그먼트(BSS): 정적 및 전역 변수로 구성된 object 파일 또는 프로그램의 가상 주소 공간의 일부
        - 초기화되지 않은 데이터 세그먼트는 BSS(Block Started by Symbol) 세그먼트라고도 함
        - 런타임 중에 변수 값이 변경될 수 있으므로 데이터 세그먼트 는 읽기-쓰기이며 이 세그먼트도 크기가 고정되어 있음
- 스택 세그먼트 (크기 가변)
    - 지역 변수 및 함수 매개변수에 할당된 메모리 영역
    - 함수 호출을 실행하는 동안 반환 주소 저장 
    - 지역 변수, 함수 매개뱐수를 저장하고 다음 주소 또는 반환 주소를 저장하기 위해 LIFO 매커니즘 사용 
    - 할당되는 데이터(지역 변수, 함수 매개변수, 함수 호출)에 따라 크기 가변적 
- 힙 세그먼트 (크기 가변)
    - malloc(), calloc() 호출과 같은 동적 메모리 저장에 할당된 메모리 영역 
    - 사용자 할당에 따라 크기 가변적 


![](https://www.tutorialspoint.com/inter_process_communication/images/process_image.jpg)

<br>

# Ubuntu18.04에서 프로세스 이미지 확인
- int 변수 할당크기 만큼 bss의 크기가 4씩 증가하는 것을 확인할 수 있음
- text: 실행 가능 명령어 (print)
- data: 초기화 한 변수
- bss: 초기화 하지 않은 변수
- 단, 변수만 선언하고 초기화하지 않은 경우에는 메모리 할당이 되지 않음 (컴파일러 최적화)

./build.sh && size segment_size1 segment_size2 segment_size3 segment_size4 segment_size5
```zsh
   text	   data	    bss	    dec	    hex	filename
   1158	    544	      8	   1710	    6ae	segment_size1
   1158	    544	      8	   1710	    6ae	segment_size2
   1158	    548	     12	   1718	    6b6	segment_size3
   1158	    552	      8	   1718	    6b6	segment_size4
   1158	    552	     16	   1726	    6be	segment_size5
```


---
refs 
- https://www.tutorialspoint.com/inter_process_communication/inter_process_communication_process_image.htm